package com.ss.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

@Transactional
@Data
@NoArgsConstructor
@Entity
@Table(name = "Book_Details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookId")
public class Book {

	@Id
	@Column(name = "BookID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	@Column(name = "BookName")
	private String bookname;
	@Column(name = "BookPrice")
	private int price;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId")
	private User user;

	public Book(String bookname, int price, User user) {
		super();
		this.bookname = bookname;
		this.price = price;
		this.user = user;
	}

	public Book(String bookname, int price, User user, int bookId) {
		super();
		this.bookname = bookname;
		this.price = price;
		this.user = user;
		this.bookId = bookId;
	}
}
