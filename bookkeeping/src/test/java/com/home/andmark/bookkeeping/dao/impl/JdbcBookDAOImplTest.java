package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

@Import(JdbcBookDAOImpl.class)
class JdbcBookDAOImplTest {
    private JdbcBookDAOImpl JdbcBookDAOImpl;

    @BeforeEach
    public void setup() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
        JdbcBookDAOImpl = new JdbcBookDAOImpl();
        JdbcBookDAOImpl.setDataSource(dataSource);
    }

    @Test
    public void whenInjectInMemoryDataSource_thenReturnCorrectEmployeeCount() {
        assertEquals(4, JdbcBookDAOImpl.getCountOfEmployees());
    }

    @Test
    public void whenSaveNewBook_thenShouldCreateBook() {
        Book expected = new Book();
        expected.setTitle("Clarissa");
        expected.setAuthor("Samuel Richardson");
        expected.setYear(1748);

        JdbcBookDAOImpl.save(expected);
        Book actual = JdbcBookDAOImpl.read(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void whenFindBookById_thenShouldReturnThisBook() {
        Book present = JdbcBookDAOImpl.read(1);
        assertEquals(true, present.getTitle().equals("Robinson Crusoe")); //in file "test-data.sql"
    }

    @Test
    void whenfindAll_thenShouldReturnAllBooks() {
        assertNotNull(JdbcBookDAOImpl.readAll());
        assertEquals(4, JdbcBookDAOImpl.readAll().size());
    }

    @Test
    void whenDeleteBook_thenShouldDeleteHimFromDatabase() {
        assertEquals(1, JdbcBookDAOImpl.delete(1));
        assertEquals(0, JdbcBookDAOImpl.delete(1));
    }
}