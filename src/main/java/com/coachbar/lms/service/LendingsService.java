package com.coachbar.lms.service;

import java.util.Optional;

import com.coachbar.lms.model.Lendings;

public interface LendingsService {
	
	Lendings saveOrUpdateIssueEntry(Lendings lending);
	
	Optional<Lendings> findByIssueCode(String issueCode);
}
