package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

@Import(JdbcPersonDAOImpl.class)
class JdbcPersonDAOImplTest {
    private JdbcPersonDAOImpl jdbcPersonDAOImpl;

    @BeforeEach
    public void setup() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
        jdbcPersonDAOImpl = new JdbcPersonDAOImpl();
        jdbcPersonDAOImpl.setDataSource(dataSource);
    }

    @Test
    public void whenInjectInMemoryDataSource_thenReturnCorrectEmployeeCount() {
        assertEquals(4, jdbcPersonDAOImpl.getCountOfEmployees());
    }

    @Test
    public void whenSaveNewPerson_thenShouldCreatePerson() {
        Person expected = new Person();
        expected.setName("Bilbo");
        expected.setPatronymic("Hobbits");
        expected.setSurname("Beggins");
        expected.setBirthday(1950);

        jdbcPersonDAOImpl.save(expected);
        Person actual = jdbcPersonDAOImpl.read(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void whenFindPersonById_thenShouldReturnThisPerson() {
        Person present = jdbcPersonDAOImpl.read(1);
        assertEquals(true, present.getName().equals("Petr")); //in file "test-data.sql"
    }

    @Test
    void whenfindAll_thenShouldReturnAllPersons() {
        assertNotNull(jdbcPersonDAOImpl.readAll());
        assertEquals(4, jdbcPersonDAOImpl.readAll().size());
    }

    @Test
    void whenDeletePerson_thenShouldDeleteHimFromDatabase() {
        assertEquals(1, jdbcPersonDAOImpl.delete(1));
        assertEquals(0, jdbcPersonDAOImpl.delete(1));
    }
}