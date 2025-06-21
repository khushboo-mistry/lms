package com.coachbar.lms.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
public class BooksController {

	@Autowired
	private BooksToBooksDtoMapper booksToBooksDtoMapper;

	@Autowired
	private BooksService booksService;

	@ApiOperation(value = "Get All Books List", notes = "To Fetch All the Books List with all details in a go.")
	@GetMapping("/books")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> getBooks(HttpServletRequest request) throws JsonProcessingException {
		List<BooksDto> booksList = new ArrayList<BooksDto>();
		try {

			List<Books> entity = booksService.getAllBooks();
			entity.forEach(book -> {
				booksList.add(booksToBooksDtoMapper.toDto(book));
			});
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ResponseGenerator.getResponse(booksList, ResponseStatusCode.S_1000));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Get Book", notes = "To Fetch All the Books List with all details in a go.")
	@GetMapping("/books/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> getBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id) throws JsonProcessingException {
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

	@ApiOperation(value = "Post Book", notes = "To add book a book to records.")
	@PostMapping("/books")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postBook(HttpServletRequest request,
			@RequestBody @Valid @ApiParam(value = "Books Dto", required = true) BooksDto bookDto)
			throws JsonProcessingException {
		try {
			booksService.saveBook(booksToBooksDtoMapper.toEntity(bookDto));
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ResponseGenerator.getResponse(bookDto, ResponseStatusCode.S_1001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Update Book", notes = "To update the book data in records.")
	@PutMapping("/books/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> putBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id,
			@RequestBody @Valid @ApiParam(value = "Book Dto", required = true) BooksDto bookDto)
			throws JsonProcessingException {
		BooksDto book = null;
		try {

			Optional<Books> entity = booksService.getBook(id);
			if (entity.isPresent()) {
				Books bookToUpdate = booksToBooksDtoMapper.toEntity(bookDto);
				bookToUpdate.setId(entity.get().getId());
				booksService.saveBook(bookToUpdate);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ResponseGenerator.getResponse(bookDto, ResponseStatusCode.S_1002));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(book, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Delete Book", notes = "To delete the book from records.")
	@DeleteMapping("/books/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> deleteBook(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "Book Id", required = true) Long id) throws JsonProcessingException {
		try {

			Optional<Books> entity = booksService.getBook(id);
			if (entity.isPresent()) {
				booksService.deleteBook(entity.get());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ResponseGenerator.getResponse(true, ResponseStatusCode.S_1003));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(false, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

}