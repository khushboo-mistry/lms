package com.coachbar.lms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachbar.lms.model.Lendings;
import com.coachbar.lms.repository.LendingsRepository;
import com.coachbar.lms.service.LendingsService;

@Service
public class LendingsServiceImpl implements LendingsService {

	@Autowired
	private LendingsRepository lendingsRepository;

	@Override
	public Lendings saveOrUpdateIssueEntry(Lendings lending) {
		return lendingsRepository.save(lending);
	}

	@Override
	public Optional<Lendings> findByIssueCode(String issueCode) {
		return lendingsRepository.findByIssueCode(issueCode);
	}



	
}
