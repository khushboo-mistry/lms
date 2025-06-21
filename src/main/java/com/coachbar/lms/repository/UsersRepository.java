package com.coachbar.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coachbar.lms.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUserCode(String userCode);

}
