package com.coachbar.lms.mapper;

import org.mapstruct.Mapper;

import com.coachbar.lms.dto.BooksDto;
import com.coachbar.lms.model.Books;

@Mapper(componentModel = "spring")
public interface BooksToBooksDtoMapper {

	Books toEntity(BooksDto dto);
	
	BooksDto toDto(Books entity);
}
