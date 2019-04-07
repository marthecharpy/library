package com.amazing.ideallibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.ideallibrary.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    public List <Book> findAllByOrderByAuthor();
}
