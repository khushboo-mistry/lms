package com.coachbar.lms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coachbar.lms.enumeration.LendingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "LENDINGS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lendings extends BaseDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Books book;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private Users user;

	@Column(name = "ISSUE_DATE", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date issueDate;

	@Column(name = "DUE_DATE", columnDefinition = "datetime")
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@Column(name = "RETURN_DATE", columnDefinition = "datetime")
	@Temporal(TemporalType.DATE)
	private Date returnDate;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private LendingStatus status;

	@Column(name = "FINE_AMOUNT")
	private Double fineAmount = 0.0;

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public LendingStatus getStatus() {
		return status;
	}

	public void setStatus(LendingStatus status) {
		this.status = status;
	}

	public Double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}

}