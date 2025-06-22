package com.coachbar.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coachbar.lms.model.Users;
import com.coachbar.lms.repository.UsersRepository;
import com.coachbar.lms.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<Users> getUser(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public Users saveUser(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public void deleteUser(Users user) {
		usersRepository.delete(user);
	}

	@Override
	public Optional<Users> getUserByUserCode(String userCode) {
		return usersRepository.findByUserCode(userCode);
	}

	
}
