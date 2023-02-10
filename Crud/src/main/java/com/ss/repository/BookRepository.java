package com.ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	public Optional<Book> getByBookIdAndBookname(Integer bookid, String bookname);

}
