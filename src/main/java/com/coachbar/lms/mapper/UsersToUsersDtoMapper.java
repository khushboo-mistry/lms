package com.coachbar.lms.mapper;

import org.mapstruct.Mapper;

import com.coachbar.lms.dto.UsersDto;
import com.coachbar.lms.model.Users;

@Mapper(componentModel = "spring")
public interface UsersToUsersDtoMapper {

	Users toEntity(UsersDto dto);

	UsersDto toDto(Users entity);
}
