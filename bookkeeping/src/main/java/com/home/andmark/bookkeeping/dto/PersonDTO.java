package com.home.andmark.bookkeeping.dto;

import com.home.andmark.bookkeeping.model.Book;
import javax.validation.constraints.*;

import java.util.ArrayList;
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

    @Min(value = 1900, message = "Birthday should be greater than 1900")
    int birthday;

    private List<Book> books;

    public PersonDTO(int id, String name, String patronymic, String surname, int birthday) {
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthday = birthday;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id && birthday == personDTO.birthday && Objects.equals(name, personDTO.name) && Objects.equals(patronymic, personDTO.patronymic) && Objects.equals(surname, personDTO.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, patronymic, surname, birthday);
    }
}
