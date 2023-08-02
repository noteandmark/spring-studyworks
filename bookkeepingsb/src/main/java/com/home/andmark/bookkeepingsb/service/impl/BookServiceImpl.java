package com.home.andmark.bookkeepingsb.service.impl;

import com.home.andmark.bookkeepingsb.dto.BookDTO;
import com.home.andmark.bookkeepingsb.dto.PersonDTO;
import com.home.andmark.bookkeepingsb.model.Book;
import com.home.andmark.bookkeepingsb.model.Person;
import com.home.andmark.bookkeepingsb.repository.BooksRepository;
import com.home.andmark.bookkeepingsb.repository.PersonsRepository;
import com.home.andmark.bookkeepingsb.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;
    private final PersonsRepository personsRepository;
    private final ModelMapper mapper;

    public BookServiceImpl(BooksRepository booksRepository, PersonsRepository personsRepository, ModelMapper mapper) {
        this.booksRepository = booksRepository;
        this.personsRepository = personsRepository;
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
        return mapListOfEntityToDTO(booksRepository.findAll());
    }

    @Override
    public List<BookDTO> findAllPaginated(int page, int booksPerPage) {
        List<Book> bookPage = booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        return mapListOfEntityToDTO(bookPage);
    }

    @Override
    public List<BookDTO> findAllSortedByYear() {
        List<Book> books = booksRepository.findAll(Sort.by(Sort.Direction.ASC, "year"));
        return mapListOfEntityToDTO(books);
    }

    @Override
    public List<BookDTO> searchByTitle(String titleInitials) {
        return mapListOfEntityToDTO(booksRepository.findByTitleStartingWith(titleInitials));
    }

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
    public void assignBookToPerson(int bookId, int personId) {
        BookDTO bookDTO = mapper.map(booksRepository.findById(bookId).get(), BookDTO.class);
        PersonDTO personDTO = mapper.map(personsRepository.findById(personId).get(), PersonDTO.class);

        bookDTO.setTakenAt(new Date());
        bookDTO.setOwner(mapper.map(personDTO, Person.class));
        booksRepository.save(mapper.map(bookDTO, Book.class));
    }

    @Override
    @Transactional
    public void releaseBook(int id) {
        BookDTO bookDTO = mapper.map(booksRepository.findById(id), BookDTO.class);
        if (bookDTO != null) {
            bookDTO.setOwner(null);
            bookDTO.setTakenAt(null);
            booksRepository.save(mapper.map(bookDTO, Book.class));
        }
    }


    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findByTitle(String bookTitle) {
        return booksRepository.findByTitle(bookTitle);
    }

    @Override
    public int countAllBooks() {
        return booksRepository.countAllBooks();
    }

    private List<BookDTO> mapListOfEntityToDTO(List<Book> all) {
        return all.stream().map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

}