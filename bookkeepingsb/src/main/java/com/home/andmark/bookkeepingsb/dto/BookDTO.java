package com.home.andmark.bookkeepingsb.dto;

import com.home.andmark.bookkeepingsb.model.Person;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.*;

//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;

public class BookDTO {
    private int id;
    private Person owner;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 200, message = "Title should be between 2 and 200 characters")
    private String title;

    @NotEmpty(message = "Author's name should not be empty")
    @Size(min = 2, max = 200, message = "Author's name should be between 2 and 200 characters")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    int year;

    private Date takenAt;

    private boolean overdue;

    public BookDTO(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public BookDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

}
