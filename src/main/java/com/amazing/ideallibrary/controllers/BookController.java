package com.amazing.ideallibrary.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.amazing.ideallibrary.entities.Author;
import com.amazing.ideallibrary.entities.Book;
import com.amazing.ideallibrary.repositories.AuthorRepository;
import com.amazing.ideallibrary.repositories.BookRepository;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @GetMapping("/")
    public String browse(Model model) {
        List <Book> books = bookRepo.findAllByOrderByAuthor();
        model.addAttribute("books", books);
        return "books/books";
    }

    @GetMapping("/books/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("author",new Author());
        return "books/create";
    }
    @PostMapping("/")
    public String store(@ModelAttribute Book book, @ModelAttribute Author author) {
        String lastname = author.getLastname();
        Author existingAuthor = authorRepo.findByLastname(lastname);
        if (existingAuthor != null) {
            book.setAuthor(existingAuthor);
        }
        else { 
            author = authorRepo.save(author);
            book.setAuthor(existingAuthor);
        }
        book = bookRepo.save(book);
        return "redirect:/";
    }
}
