package com.lms.model;

public class Book {
	//private int bookID;
	private String ISBN;
	//private String status;
	private String title;
	private String author;
	private String publisher;
	private String price;
	private String genre;
	
	//public void setBookID(int bookID) {this.bookID = bookID;}
	public void setISBN(String ISBN) {this.ISBN = ISBN;}
	//public void setStatus(String status) {this.status = status;}
	public void setTitle(String title) {this.title = title;}
	public void setAuthor(String author) {this.author = author;}
	public void setPublisher(String publisher) {this.publisher = publisher;}
	public void setPrice(String price) {this.price = price;}
	public void setGenre(String genre) {this.genre = genre;}
	
	
	public void setDetails(String ISBN, String title , String author , String publisher , String price , String genre ) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.genre = genre;
	}
	
	//public int getBookID() {return bookID;}
	public String getISBN() {return ISBN;}
	//public String getStatus() {return status;}
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public String getPublisher() {return publisher;}
	public String getPrice() {return price;}
	public String getGenre() {return genre;}
	

	
}
