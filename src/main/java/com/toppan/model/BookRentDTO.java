package com.toppan.model;

import java.util.List;

public class BookRentDTO {

	private int bookId;
	
	private String author;

	private String book;

	private List<PersonRentDTO> personRentList;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public List<PersonRentDTO> getPersonRentList() {
		return personRentList;
	}

	public void setPersonRentList(List<PersonRentDTO> personRentList) {
		this.personRentList = personRentList;
	}
}
