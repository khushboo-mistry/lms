package com.coachbar.lms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coachbar.lms.enumeration.BookCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BOOKS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Books extends BaseDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TITLE", length = 500, nullable = false)
	private String title;

	@Column(name = "AUTHER", nullable = false)
	private String auther;

	@Column(name = "PUBLICATION_YEAR", nullable = false)
	private String publicationYear;

	@Column(name = "PUBLICATION_DATE", columnDefinition = "datetime")
	@Temporal(TemporalType.DATE)
	private Date publicationDate;

	@Column(name = "PUBLISHER", nullable = false)
	private String publisher;

	@Column(name = "ISBN", length = 50)
	private String isbn;

	@Column(name = "CATEGORY", nullable = false)
	@Enumerated(EnumType.STRING)
	private BookCategory category;

	@Column(name = "PAGES")
	private Integer pages;

	@Column(name = "EDITION")
	private Integer edition;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "QUANTITY_TOTAL", columnDefinition = "INTEGER DEFAULT 0")
	private Integer quantityTotal;

	@Column(name = "QUANTITY_AVAILABLE", columnDefinition = "INTEGER DEFAULT 0")
	private Integer quantityAvailable;
	
	@Column(name = "LANGUAGE", columnDefinition = "VARCHAR(255) DEFAULT 'English'")
	private String language;
	
	@Column(name = "CODE", nullable = false)
	private String code;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<Lendings> lendings;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
