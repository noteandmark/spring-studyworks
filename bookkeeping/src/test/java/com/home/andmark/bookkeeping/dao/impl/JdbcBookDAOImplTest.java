package com.home.andmark.bookkeeping.dao.impl;

import org.springframework.context.annotation.Import;

@Import(BookDAOImpl.class)
class JdbcBookDAOImplTest {
//    private JdbcBookDAOImpl jdbcBookDAO;
//
//    @BeforeEach
//    public void setup() {
//        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:schema.sql")
//                .addScript("classpath:test-data.sql")
//                .build();
//        jdbcBookDAO = new JdbcBookDAOImpl();
//        jdbcBookDAO.setDataSource(dataSource);
//    }
//
//    @Test
//    public void whenInjectInMemoryDataSource_thenReturnCorrectEmployeeCount() {
//        assertEquals(5, jdbcBookDAO.getCountOfEmployees());
//    }
//
//    @Test
//    public void whenSaveNewBook_thenShouldCreateBook() {
//        Book expected = new Book();
//        expected.setTitle("Clarissa");
//        expected.setAuthor("Samuel Richardson");
//        expected.setYear(1748);
//
//        jdbcBookDAO.save(expected);
//        Book actual = jdbcBookDAO.read(expected.getId());
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void whenFindBookById_thenShouldReturnThisBook() {
//        Book present = jdbcBookDAO.read(1);
//        assertEquals(true, present.getTitle().equals("Robinson Crusoe")); //in file "test-data.sql"
//    }
//
//    @Test
//    void whenfindAll_thenShouldReturnAllBooks() {
//        assertNotNull(jdbcBookDAO.readAll());
//        assertEquals(5, jdbcBookDAO.readAll().size());
//    }
//
//    @Test
//    void whenDeleteBook_thenShouldDeleteHimFromDatabase() {
//        assertEquals(1, jdbcBookDAO.delete(1));
//        assertEquals(0, jdbcBookDAO.delete(1));
//    }
//
//    @Test
//    void whenGetBooksByPersonId_thenShouldReturnListBooks() {
//        int personId = 2;
//        List<Book> books = jdbcBookDAO.getBooksByPersonId(personId);
//        assertEquals(2, books.size()); // Assuming person with id 2 has borrowed two books
//
//        // Assert individual book properties
//        Book firstBook = books.get(0);
//        assertEquals("Gulliver’s Travels", firstBook.getTitle());
//        assertEquals("Jonathan Swift", firstBook.getAuthor());
//        assertEquals(1726, firstBook.getYear());
//    }
}