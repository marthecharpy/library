package com.amazing.ideallibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/books/{id}")
    public String read(Model model, @PathVariable Long id) {
        model.addAttribute("book", bookRepo.findById(id).get());
        model.addAttribute("author", authorRepo.findByBooksId(id));
        return "books/edit";
    }

    @PutMapping("/books/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Book book, @ModelAttribute Author author) {
        String lastname = author.getLastname();
        Author authorToUpdate = authorRepo.findByLastname(lastname);
        if (author.getLastname()!= null) {
            authorToUpdate.setLastname(author.getLastname());
        }
        if (author.getFirstname()!= null) {
            authorToUpdate.setFirstname(author.getFirstname());
        }
        if (author.getNationality()!= null) {
            authorToUpdate.setNationality(author.getNationality());
        }
        authorToUpdate = authorRepo.save(authorToUpdate);

        Book bookToUpdate = bookRepo.findById(id).get();
        if (book.getTitle()!= null) {
            bookToUpdate.setTitle(book.getTitle());
        }
        if (book.getResume()!= null) {
            bookToUpdate.setResume(book.getResume());
        }
        if (book.getDate()!= null) {
            bookToUpdate.setDate(book.getDate());
        }
        if (book.getOpinion()!= null) {
            bookToUpdate.setOpinion(book.getOpinion());
        }
        bookToUpdate.setAuthor(authorToUpdate);
        bookToUpdate = bookRepo.save(bookToUpdate);
        return "redirect:/";
    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable Long id) {
        bookRepo.deleteById(id);
        return "redirect:/";
    }
}
