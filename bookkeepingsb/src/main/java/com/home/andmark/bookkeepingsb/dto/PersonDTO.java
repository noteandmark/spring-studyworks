package com.home.andmark.bookkeepingsb.dto;

import com.home.andmark.bookkeepingsb.model.Book;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Size;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonDTO {
//    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Patronymic should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String patronymic;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 100, message = "Surname should be between 2 and 100 characters")
    private String surname;

    private Date dateOfBirth;

    private Date createdAt;

    private String password;

    private String role;

    private List<Book> books;

    public PersonDTO(String name, String patronymic, String surname) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
    }

    public PersonDTO() {

    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
