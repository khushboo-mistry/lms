package com.coachbar.lms.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coachbar.lms.dto.BooksDto;
import com.coachbar.lms.dto.LendingsDto;
import com.coachbar.lms.dto.Response;
import com.coachbar.lms.dto.ResponseGenerator;
import com.coachbar.lms.dto.ResponseStatusCode;
import com.coachbar.lms.enumeration.LendingStatus;
import com.coachbar.lms.model.Books;
import com.coachbar.lms.model.Lendings;
import com.coachbar.lms.model.Users;
import com.coachbar.lms.service.BooksService;
import com.coachbar.lms.service.LendingsService;
import com.coachbar.lms.service.UsersService;
import com.coachbar.lms.util.CodeGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class BooksIssueController {

	private static final String FINE_AMOUNT = "INR: 5/day";

	@Autowired
	private BooksService booksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private LendingsService lendingsService;

	@ApiOperation(value = "Issue Book", notes = "To issue book to user.")
	@PostMapping("/issues/{userCode}/{bookCode}")
	public ResponseEntity<Response<LendingsDto>> issueBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "User Code", required = true) String userCode,
			@PathVariable @Valid @ApiParam(value = "Book Code", required = true) String bookCode)
			throws JsonProcessingException {
		try {

			Optional<Users> user = usersService.getUserByUserCode(userCode);
			if (!user.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2002));
			}
			Optional<Books> book = booksService.getBookByBookCode(bookCode);
			if (!book.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2001));
			}

			Date issueDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(issueDate);
			calendar.add(Calendar.MONTH, 1);
			Date dueDate = calendar.getTime();

			Lendings lendings = new Lendings();
			lendings.setBook(book.get());
			lendings.setUser(user.get());
			lendings.setIssueCode(CodeGenerator.generateCode());
			lendings.setIssueDate(issueDate);
			lendings.setStatus(LendingStatus.ISSUED);
			lendings.setDueDate(dueDate);
			lendings = lendingsService.saveOrUpdateIssueEntry(lendings);
			
			LendingsDto lendingsDto = new LendingsDto();
			lendingsDto.setBookCode(bookCode);
			lendingsDto.setBookName(book.get().getTitle());
			lendingsDto.setFineChargerPerDay(FINE_AMOUNT);
			lendingsDto.setIssuedDate(issueDate.toString());
			lendingsDto.setDueDate(dueDate.toString());
			lendingsDto.setReturnDate("-");
			lendingsDto.setFineAmount("-");
			lendingsDto.setUserCode(userCode);
			lendingsDto.setUserName(user.get().getName() + " " + user.get().getSurname());
			lendingsDto.setIssueCode(lendings.getIssueCode());

			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(lendingsDto, ResponseStatusCode.S_1008));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Return Book", notes = "To initiate return for a book issued in record.")
	@PostMapping("/issues/{issueCode}/return")
	public ResponseEntity<Response<LendingsDto>> returnBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Issue Code", required = true) String issueCode)
			throws JsonProcessingException {
		try {

			Optional<Lendings> lending = lendingsService.findByIssueCode(issueCode);
			if (!lending.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2003));
			}
			if (lending.get().getStatus() == LendingStatus.OVERDUE) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2004));
			}

			Lendings lendings = lending.get();
			lendings.setReturnDate(new Date());
			lendings.setStatus(LendingStatus.RETURNED);
			lendings = lendingsService.saveOrUpdateIssueEntry(lendings);
			
			LendingsDto lendingsDto = new LendingsDto();
			lendingsDto.setBookCode(lendings.getBook().getCode());
			lendingsDto.setBookName(lendings.getBook().getTitle());
			lendingsDto.setFineChargerPerDay(FINE_AMOUNT);
			lendingsDto.setIssuedDate(lendings.getIssueDate().toString());
			lendingsDto.setReturnDate(lendings.getReturnDate().toString());
			lendingsDto.setUserCode(lendings.getUser().getUserCode());
			lendingsDto.setUserName(lendings.getUser().getName() + " " + lendings.getUser().getSurname());
			lendingsDto.setIssueCode(lendings.getIssueCode());

			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(lendingsDto, ResponseStatusCode.S_1009));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Calculate fine", notes = "To Calculate fine for a book issued in record.")
	@PostMapping("/issues/{issueCode}/fine")
	public ResponseEntity<Response<LendingsDto>> calculateFineOnIssuedBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Issue Code", required = true) String issueCode)
			throws JsonProcessingException {
		try {

			Optional<Lendings> lending = lendingsService.findByIssueCode(issueCode);
			if (!lending.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2003));
			}

			Lendings lendings = lending.get();

			Date issueDate = lendings.getIssueDate();
			Date dueDate = lendings.getDueDate();
			long diffInDates = dueDate.getTime() - issueDate.getTime();
			long extraDays = diffInDates / (1000 * 60 * 60 * 24);

			Double fineAmount = extraDays * 5.0;
			Date returnDate = new Date();

			lendings.setFineAmount(fineAmount);
			lendings.setReturnDate(returnDate);
			lendings.setStatus(LendingStatus.OVERDUE);
			lendings = lendingsService.saveOrUpdateIssueEntry(lendings);

			LendingsDto lendingsDto = new LendingsDto();
			lendingsDto.setBookCode(lendings.getBook().getCode());
			lendingsDto.setBookName(lendings.getBook().getTitle());
			lendingsDto.setFineChargerPerDay(FINE_AMOUNT);
			lendingsDto.setFineAmount(fineAmount.toString());
			lendingsDto.setIssuedDate(lendings.getIssueDate().toString());
			lendingsDto.setDueDate(lendings.getDueDate().toString());
			lendingsDto.setReturnDate(returnDate.toString());
			lendingsDto.setUserCode(lendings.getUser().getUserCode());
			lendingsDto.setUserName(lendings.getUser().getName() + " " + lendings.getUser().getSurname());
			lendingsDto.setIssueCode(lendings.getIssueCode());

			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(lendingsDto, ResponseStatusCode.S_1010));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}
	
	@ApiOperation(value = "Pay fine", notes = "To initiate return with fine for a book issued in record.")
	@PostMapping("/issues/{issueCode}/fine/pay")
	public ResponseEntity<Response<LendingsDto>> payFineOnIssuedBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Issue Code", required = true) String issueCode)
			throws JsonProcessingException {
		try {

			Optional<Lendings> lending = lendingsService.findByIssueCode(issueCode);
			if (!lending.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(null, ResponseStatusCode.CE_2003));
			}

			Lendings lendings = lending.get();
			lendings.setReturnDate(new Date());
			lendings.setStatus(LendingStatus.RETURNED);
			lendings = lendingsService.saveOrUpdateIssueEntry(lendings);

			LendingsDto lendingsDto = new LendingsDto();
			lendingsDto.setBookCode(lendings.getBook().getCode());
			lendingsDto.setBookName(lendings.getBook().getTitle());
			lendingsDto.setFineChargerPerDay(FINE_AMOUNT);
			lendingsDto.setFineAmount(lendings.getFineAmount().toString());
			lendingsDto.setIssuedDate(lendings.getIssueDate().toString());
			lendingsDto.setDueDate(lendings.getDueDate().toString());
			lendingsDto.setReturnDate(lendings.getReturnDate().toString());
			lendingsDto.setUserCode(lendings.getUser().getUserCode());
			lendingsDto.setUserName(lendings.getUser().getName() + " " + lendings.getUser().getSurname());
			lendingsDto.setIssueCode(lendings.getIssueCode());

			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(lendingsDto, ResponseStatusCode.S_1010));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

}
