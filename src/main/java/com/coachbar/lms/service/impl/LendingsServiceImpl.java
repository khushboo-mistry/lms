package com.coachbar.lms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachbar.lms.enumeration.LendingStatus;
import com.coachbar.lms.model.Books;
import com.coachbar.lms.model.Lendings;
import com.coachbar.lms.repository.LendingsRepository;
import com.coachbar.lms.service.BooksService;
import com.coachbar.lms.service.LendingsService;

@Service
public class LendingsServiceImpl implements LendingsService {

	@Autowired
	private LendingsRepository lendingsRepository;

	@Autowired
	private BooksService booksService;

	@Override
	public Lendings saveOrUpdateIssueEntry(Lendings lending) {
		if (lending.getStatus() == LendingStatus.ISSUED) {
			Optional<Books> book = booksService.getBookByBookCode(lending.getBook().getCode());
			if (book.isPresent()) {
				Books updateAvailableBookCount = book.get();
				updateAvailableBookCount.setQuantityAvailable(
						book.get().getQuantityAvailable() != 0 ? (book.get().getQuantityAvailable() - 1) : 0);
				booksService.saveBook(updateAvailableBookCount);
			}
		}
		if (lending.getStatus() == LendingStatus.RETURNED) {
			Optional<Books> book = booksService.getBookByBookCode(lending.getBook().getCode());
			if (book.isPresent()) {
				Books updateAvailableBookCount = book.get();
				updateAvailableBookCount.setQuantityAvailable(
						book.get().getQuantityAvailable() != 0 ? (book.get().getQuantityAvailable() + 1) : 0);
				booksService.saveBook(updateAvailableBookCount);
			}
		}
		return lendingsRepository.save(lending);
	}

	@Override
	public Optional<Lendings> findByIssueCode(String issueCode) {
		return lendingsRepository.findByIssueCode(issueCode);
	}

}
