package com.home.andmark.bookkeeping.dto;

import com.home.andmark.bookkeeping.model.Person;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id && year == bookDTO.year && Objects.equals(owner, bookDTO.owner) && Objects.equals(title, bookDTO.title) && Objects.equals(author, bookDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, title, author, year);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
