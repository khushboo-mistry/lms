package com.coachbar.lms.rest;

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
import com.coachbar.lms.dto.ResponseGenerator;
import com.coachbar.lms.dto.ResponseStatusCode;
import com.coachbar.lms.mapper.BooksToBooksDtoMapper;
import com.coachbar.lms.model.Books;
import com.coachbar.lms.service.BooksService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class BooksIssueController {

	@Autowired
	private BooksToBooksDtoMapper booksToBooksDtoMapper;

	@Autowired
	private BooksService booksService;
	
	@ApiOperation(value = "Issue Book", notes = "To issue book to user.")
	@PostMapping("/books/{id}/issues")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> issueBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id)
			throws JsonProcessingException {
		BooksDto book = null;
		try {

			Optional<Books> entity = booksService.getBook(id);
			if (entity.isPresent()) {
				book = booksToBooksDtoMapper.toDto(entity.get());
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(book, ResponseStatusCode.S_1000));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(book, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}
	
	@ApiOperation(value = "Return Book", notes = "To initiate return for a book issued in record.")
	@PostMapping("/books/{id}/issues/{issueId}/return")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> returnBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id)
			throws JsonProcessingException {
		BooksDto book = null;
		try {

			Optional<Books> entity = booksService.getBook(id);
			if (entity.isPresent()) {
				book = booksToBooksDtoMapper.toDto(entity.get());
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(book, ResponseStatusCode.S_1000));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(book, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}
	
	@ApiOperation(value = "Calculate fine", notes = "To initiate return for a book issued in record.")
	@PostMapping("/books/{id}/issues/{issueId}/penalty")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> calculateFineOnIssuedBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id)
			throws JsonProcessingException {
		BooksDto book = null;
		try {

			Optional<Books> entity = booksService.getBook(id);
			if (entity.isPresent()) {
				book = booksToBooksDtoMapper.toDto(entity.get());
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(book, ResponseStatusCode.S_1000));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(book, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

}
