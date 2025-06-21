package com.coachbar.lms.service;

import java.util.List;
import java.util.Optional;

import com.coachbar.lms.model.Books;

public interface BooksService {

	List<Books> getAllBooks();
	
	Optional<Books> getBook(Long id);
	
	Books saveBook(Books book);
	
	void deleteBook(Books book);

}
