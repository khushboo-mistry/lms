package com.coachbar.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachbar.lms.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>{

	Optional<Books> findByCode(String bookCode);

}