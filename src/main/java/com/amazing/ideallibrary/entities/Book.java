package com.amazing.ideallibrary.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String resume;
    private String date;
    private String opinion;

    public Book() {
    }

    public Book(String title, String resume, String date, String opinion) {
        this.title = title;
        this.resume = resume;
        this.date = date;
        this.opinion = opinion;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getDate() {
        return date;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
