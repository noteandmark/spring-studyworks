package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.dto.BookDTO;
import com.home.andmark.bookkeeping.dto.PersonDTO;
import com.home.andmark.bookkeeping.model.Book;
import com.home.andmark.bookkeeping.model.Person;
import com.home.andmark.bookkeeping.repository.BooksRepository;
import com.home.andmark.bookkeeping.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BooksRepository booksRepository;
    private final ModelMapper mapper;

    public BookServiceImpl(BooksRepository booksRepository, ModelMapper mapper) {
        this.booksRepository = booksRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(BookDTO bookDTO) {
        booksRepository.save(mapper.map(bookDTO, Book.class));
    }

    @Override
    public BookDTO findOne(int id) {
        BookDTO foundBook = mapper.map
                (booksRepository.findById(id).get(), BookDTO.class);
        return foundBook;
    }

    @Override
    public List<BookDTO> findAll() {
        return mapListOfEntityToDTO(booksRepository.findAll());    }

    @Override
    @Transactional
    public void update(int id, BookDTO updatedBookDTO) {
        updatedBookDTO.setId(id);
        booksRepository.save(mapper.map(updatedBookDTO, Book.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void assignBookToPerson(BookDTO bookDTO, PersonDTO personDTO) {
        bookDTO.setOwner(mapper.map(personDTO, Person.class));
        booksRepository.save(mapper.map(bookDTO, Book.class));
    }

    @Override
    @Transactional
    public void releaseBook(int id) {
        BookDTO bookDTO = mapper.map(booksRepository.findById(id), BookDTO.class);
        if (bookDTO != null) {
            bookDTO.setOwner(null);
            booksRepository.save(mapper.map(bookDTO, Book.class));
        }
    }

    private List<BookDTO> mapListOfEntityToDTO(List<Book> all) {
        return all.stream().map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

//    private final BookDAO bookDAO;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public BookServiceImpl(BookDAO bookDAO, ModelMapper mapper) {
//        this.bookDAO = bookDAO;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public void save(BookDTO bookDTO) {
//        Book book = mapper.map(bookDTO, Book.class);
//        bookDAO.save(book);
//    }
//
//    @Override
//    public BookDTO findOne(int id) {
//        Book book = bookDAO.read(id);
//        BookDTO bookDTO = mapper.map(book, BookDTO.class);
//        return bookDTO;
//    }
//
//    @Override
//    public List<BookDTO> findAll() {
//        return mapListOfEntityToDTO(bookDAO.readAll());
//    }
//
//    @Override
//    public void update(int id, BookDTO bookDTO) {
//        bookDAO.update(id, mapper.map(bookDTO, Book.class));
//    }
//
//    @Override
//    public void delete(int id) {
//        bookDAO.delete(id);
//    }
//
//    private List<BookDTO> mapListOfEntityToDTO(List<Book> all) {
//        return all.stream().map(book -> mapper.map(book, BookDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void assignBookToPerson(BookDTO bookdTO, PersonDTO personDTO) {
//
//        bookdTO.setOwner(mapper.map(personDTO, Person.class));
//        bookDAO.update(bookdTO.getId(),mapper.map(bookdTO,Book.class));
//
////        BookDTO bookDTO = read(bookId);
////        if (bookDTO != null) {
////            PersonDTO personDTO = read(personId);
////            if (personDTO != null) {
////                bookDTO.setOwner(personDTO);
////                update(bookId, bookDTO);
////            } else {
////                // Handle the case when the person with the given ID is not found
////                // You can throw an exception or handle it based on your requirements
////            }
////        } else {
////            // Handle the case when the book with the given ID is not found
////            // You can throw an exception or handle it based on your requirements
////        }
////        Book book = bookDAO.read(bookId);
////
////        book.setPersonId(personId);
////        bookDAO.update(bookId, book);
//    }
//
//    private PersonDTO readPerson(int personId) {
//        // Implement the logic to read a person by their ID using the PersonDAO or PersonService
//        // and return the corresponding PersonDTO
//        return null;
//    }
//
//    @Override
//    public void releaseBook(int id) {
//        BookDTO bookDTO = mapper.map(bookDAO.read(id), BookDTO.class);
//        if (bookDTO != null) {
//            bookDTO.setOwner(null);
//            bookDAO.update(id, mapper.map(bookDTO, Book.class));
//        }
//    }
}
