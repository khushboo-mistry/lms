package com.coachbar.lms.service;

import java.util.List;
import java.util.Optional;

import com.coachbar.lms.model.Users;

public interface UsersService {

	List<Users> getAllUsers();
	
	Optional<Users> getUser(Long id);
	
	Users saveUser(Users user);
	
	void deleteUser(Users user);

	Optional<Users> getUserByUserCode(String userCode);

}
