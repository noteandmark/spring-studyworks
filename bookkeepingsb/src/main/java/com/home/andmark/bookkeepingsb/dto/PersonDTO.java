package com.home.andmark.bookkeepingsb.dto;

import com.home.andmark.bookkeepingsb.model.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonDTO {
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && Objects.equals(name, personDTO.name) && Objects.equals(patronymic, personDTO.patronymic) && Objects.equals(surname, personDTO.surname) && Objects.equals(dateOfBirth, personDTO.dateOfBirth) && Objects.equals(createdAt, personDTO.createdAt) && Objects.equals(books, personDTO.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, patronymic, surname, dateOfBirth, createdAt, books);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", books=" + books +
                '}';
    }
}
