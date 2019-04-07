package com.amazing.ideallibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.ideallibrary.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    Author findByLastname(String lastname);
    Author findByBooksId(Long bookId);

}
