package com.coachbar.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachbar.lms.model.Books;
import com.coachbar.lms.repository.BooksRepository;
import com.coachbar.lms.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;

	@Override
	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}

	@Override
	public Optional<Books> getBook(Long id) {
		return booksRepository.findById(id);
	}

	@Override
	public Books saveBook(Books book) {
		return booksRepository.save(book);
	}

	@Override
	public void deleteBook(Books book) {
		booksRepository.delete(book);
	}

	@Override
	public Optional<Books> getBookByBookCode(String bookCode) {
		return booksRepository.findByCode(bookCode);
	}
}
