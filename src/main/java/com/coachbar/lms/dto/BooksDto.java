package com.coachbar.lms.dto;

import java.util.Date;

import com.coachbar.lms.enumeration.BookCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Books", description = "Book Information")
public class BooksDto {

	@ApiModelProperty(value = "Title", required = true, example = "Head First Java")
	private String title;

	@ApiModelProperty(value = "Auther/Authers", required = true, example = "Kathy Sierra and Bert Bates")
	private String auther;

	@ApiModelProperty(value = "Year of publication", required = true, example = "2012")
	private String publicationYear;

	@ApiModelProperty(value = "Date of publication", example = "2012-01-01")
	private Date publicationDate;

	@ApiModelProperty(value = "Publisher", required = true, example = "O'Reilly Media")
	private String publisher;

	@ApiModelProperty(value = "ISBN", example = "9781491910740")
	private String isbn;

	@ApiModelProperty(value = "Category", required = true, example = "EDUCATION")
	private BookCategory category;

	@ApiModelProperty(value = "Total Pages", example = "754")
	private Integer pages;

	@ApiModelProperty(value = "Edition", example = "3")
	private Integer edition;

	@ApiModelProperty(value = "Title", example = "Complete Java learning book")
	private String description;

	@ApiModelProperty(value = "Total Quantity", example = "50")
	private Integer quantityTotal;

	@ApiModelProperty(value = "Available Quantity", example = "34")
	private Integer quantityAvailable;
	
	@ApiModelProperty(value = "Language", example = "English")
	private String language;
	
	@ApiModelProperty(value = "Code", example = "KEHYBEHDTQ")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String code;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(Integer quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
