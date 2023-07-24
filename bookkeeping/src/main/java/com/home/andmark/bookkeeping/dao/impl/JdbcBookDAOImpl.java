package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.dao.BookDAO;
import com.home.andmark.bookkeeping.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcBookDAOImpl implements BookDAO {
    private static final String SQL_INSERT_BOOK = "INSERT INTO book(title,author,year) VALUES(?,?,?);";
    private static final String SQL_GETCOUNT_BOOK = "SELECT COUNT(*) FROM book";
    private static final String SQL_FIND_BOOK = "SELECT id,person_id,title,author,year FROM book WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT id,person_id,title,author,year FROM book";
    private static final String SQL_UPDATE_BOOK = "UPDATE book set person_id = ?, title = ?, author  = ?, year = ? where id = ?";
    private static final String SQL_DELETE_BOOK = "DELETE FROM book WHERE id=?";
    private static final String SQL_GETBOOKS_FORPERSON = "SELECT id, title, author, year FROM book WHERE person_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcBookDAOImpl() {
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCountOfEmployees() {
        return jdbcTemplate.queryForObject(SQL_GETCOUNT_BOOK, Integer.class);
    }

    @Override
    public Book save(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_BOOK, new String[]{"id"});
                    ps.setString(1, book.getTitle());
                    ps.setString(2, book.getAuthor());
                    ps.setInt(3, book.getYear());
                    return ps;
                },
                keyHolder);
        book.setId(keyHolder.getKey().intValue());
        return book;
    }

    @Override
    public Book read(int id) {
        return jdbcTemplate.query(SQL_FIND_BOOK, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Book> readAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new BeanPropertyRowMapper<Book>(Book.class));
    }

    @Override
    public Book update(int id, Book updatedbook) {
        jdbcTemplate.update(SQL_UPDATE_BOOK,
                updatedbook.getPersonId(),
                updatedbook.getTitle(),
                updatedbook.getAuthor(),
                updatedbook.getYear(),
                id);
        return updatedbook;
    }

    //reserve method for multiply update; is not used in this version of the task
    public void batchUpdate(List<Book> people) {
        jdbcTemplate.batchUpdate(SQL_UPDATE_BOOK, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, people.get(i).getId());
                ps.setString(2, people.get(i).getTitle());
                ps.setString(3, people.get(i).getAuthor());
                ps.setInt(4, people.get(i).getYear());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE_BOOK, id);
    }

    public List<Book> getBooksByPersonId(int personId) {
        String sql = SQL_GETBOOKS_FORPERSON;
        return jdbcTemplate.query(sql, new Object[]{personId}, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            int year = rs.getInt("year");

            return new Book(id, personId, title, author, year);
        });
    }
}
