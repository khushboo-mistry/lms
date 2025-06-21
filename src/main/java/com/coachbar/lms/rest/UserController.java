package com.coachbar.lms.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coachbar.lms.dto.ResponseGenerator;
import com.coachbar.lms.dto.ResponseStatusCode;
import com.coachbar.lms.dto.UsersDto;
import com.coachbar.lms.mapper.UsersToUsersDtoMapper;
import com.coachbar.lms.model.Users;
import com.coachbar.lms.service.UsersService;
import com.coachbar.lms.util.CodeGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {

	@Autowired
	private UsersToUsersDtoMapper usersToUsersDtoMapper;

	@Autowired
	private UsersService usersService;

	@ApiOperation(value = "Get All Users List", notes = "To Fetch All the users.")
	@GetMapping("/users")
	public ResponseEntity<?> getUsers(HttpServletRequest request) throws JsonProcessingException {
		List<UsersDto> usersList = new ArrayList<UsersDto>();
		try {

			List<Users> entity = usersService.getAllUsers();
			entity.forEach(user -> {
				usersList.add(usersToUsersDtoMapper.toDto(user));
			});
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ResponseGenerator.getResponse(usersList, ResponseStatusCode.S_1000));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Get User", notes = "To the User data from records.")
	@GetMapping("/users/{userCode}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> getUser(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "User Code", required = true) String userCode) throws JsonProcessingException {
		UsersDto user = null;
		try {

			Optional<Users> entity = usersService.getUserByUserCode(userCode);
			if (entity.isPresent()) {
				user = usersToUsersDtoMapper.toDto(entity.get());
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseGenerator.getResponse(user, ResponseStatusCode.S_1000));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(user, ResponseStatusCode.CE_2002));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Post User", notes = "To add a user to records.")
	@PostMapping("/users")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> postUser(HttpServletRequest request,
			@RequestBody @Valid @ApiParam(value = "User Dto", required = true) UsersDto userDto)
			throws JsonProcessingException {
		try {
			Users user = usersToUsersDtoMapper.toEntity(userDto);
			user.setUserCode(CodeGenerator.generateCode());
			usersService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ResponseGenerator.getResponse(userDto, ResponseStatusCode.S_1005));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Update User", notes = "To update the user data in records.")
	@PutMapping("/users/{userCode}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> putUser(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "User Code", required = true) String userCode,
			@RequestBody @Valid @ApiParam(value = "User Dto", required = true) UsersDto userDto)
			throws JsonProcessingException {
		UsersDto book = null;
		try {

			Optional<Users> entity = usersService.getUserByUserCode(userCode);
			if (entity.isPresent()) {
				Users userToUpdate = usersToUsersDtoMapper.toEntity(userDto);
				userToUpdate.setId(entity.get().getId());
				usersService.saveUser(userToUpdate);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ResponseGenerator.getResponse(userDto, ResponseStatusCode.S_1006));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(book, ResponseStatusCode.CE_2001));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

	@ApiOperation(value = "Delete User", notes = "To delete the user from records.")
	@DeleteMapping("/users/{userCode}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "x-api-key", value = "Example: A0FD5C94164A5EB7A4224ACCB46EB4B5", paramType = "header", required = true) })
	public ResponseEntity<?> deleteUser(HttpServletRequest request,
			@PathVariable @Valid @ApiParam(value = "User Code", required = true) String userCode) throws JsonProcessingException {
		try {

			Optional<Users> entity = usersService.getUserByUserCode(userCode);
			if (entity.isPresent()) {
				usersService.deleteUser(entity.get());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ResponseGenerator.getResponse(true, ResponseStatusCode.S_1007));
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseGenerator.getResponse(false, ResponseStatusCode.CE_2002));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseGenerator.handleException(e));
		}
	}

}