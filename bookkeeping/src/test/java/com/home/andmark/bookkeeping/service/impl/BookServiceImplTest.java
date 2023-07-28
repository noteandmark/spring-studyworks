package com.home.andmark.bookkeeping.service.impl;

import com.home.andmark.bookkeeping.config.SpringConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
//    @Mock
//    private JdbcBookDAOImpl bookDAO;
//    private BookDTO bookDTO;
//    private Book book;
//
//    @Mock
//    private ModelMapper mapper;
//
//    @InjectMocks
//    private BookServiceImpl bookService;
//
//    @BeforeEach
//    void setUp() {
//        bookDTO = new BookDTO();
//        book = new Book();
//
//        Mockito.lenient().when(mapper.map(bookDTO, Book.class)).thenReturn(book);
//        Mockito.lenient().when(mapper.map(book, BookDTO.class)).thenReturn(bookDTO);
//
//        bookDTO.setId(5);
//        bookDTO.setTitle("Tati");
//        bookDTO.setAuthor("Mich");
//        bookDTO.setYear(1988);
//    }
//
//    @Test
//    void save() {
//        when(bookDAO.save(any())).thenReturn(new Book());
//        bookService.save(bookDTO);
//        verify(bookDAO, only()).save(any(Book.class));
//    }
//
//    @Test
//    void read() {
//        book.setId(5);
//        book.setTitle("Tati");
//        book.setAuthor("Mich");
//        book.setYear(1988);
//
//        when(bookDAO.read(anyInt())).thenReturn(book);
//        BookDTO actual = bookService.read(5);
//        assertEquals(bookDTO, actual);
//    }
//
//    @Test
//    void findAll() {
//        List<Book> all = new ArrayList<>();
//        all.add(book);
//        when(bookDAO.readAll()).thenReturn(all);
//        bookService.readAll();
//        verify(bookDAO, only()).readAll();
//    }
//
//    @Test
//    void update() {
//        bookDTO.setTitle("New");
//        bookDTO.setAuthor("Person");
//        bookService.update(bookDTO.getId(),bookDTO);
//        verify(bookDAO,times(1)).update(bookDTO.getId(),book);
//    }
//
//    @Test
//    void delete() {
//        book.setId(5);
//        bookService.delete(bookDTO.getId());
//        verify(bookDAO,times(1)).delete(book.getId());
//    }

}