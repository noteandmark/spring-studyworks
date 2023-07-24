package com.home.andmark.bookkeeping.util;

import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BookDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BookDTO bookDTO = (BookDTO) o;

        // Проверяем, что у автора книги имя начинается с заглавной буквы
        // Если имя не начинается с заглавной буквы - выдаем ошибку
        if (!Character.isUpperCase(bookDTO.getAuthor().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");
    }
}
