package com.coachbar.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachbar.lms.model.Lendings;

@Repository
public interface LendingsRepository extends JpaRepository<Lendings, Long> {

	Optional<Lendings> findByIssueCode(String issueCode);

}
