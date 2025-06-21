package com.coachbar.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachbar.lms.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>{

}