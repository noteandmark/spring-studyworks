package com.home.andmark.bookkeeping.dao.impl;

import com.home.andmark.bookkeeping.dao.PersonDAO;
import com.home.andmark.bookkeeping.model.Person;

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
public class JdbcPersonDAOImpl implements PersonDAO {
    private static final String SQL_INSERT_PERSON = "INSERT INTO person(name,patronymic,surname,birthday) VALUES(?,?,?,?);";
    private static final String SQL_GETCOUNT_PERSON = "SELECT COUNT(*) FROM person";
    private static final String SQL_FIND_PERSON = "SELECT id,name,patronymic,surname,birthday FROM person WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT id,name,patronymic,surname,birthday FROM person";
    private static final String SQL_UPDATE_PERSON = "UPDATE person set name = ?, surname = ?, patronymic  = ?, birthday = ? where id = ?";
    private static final String SQL_DELETE_PERSON = "DELETE FROM person WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcPersonDAOImpl() {
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCountOfEmployees() {
        return jdbcTemplate.queryForObject(SQL_GETCOUNT_PERSON, Integer.class);
    }

    @Override
    public Person save(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_PERSON, new String[]{"id"});
                    ps.setString(1, person.getName());
                    ps.setString(2, person.getPatronymic());
                    ps.setString(3, person.getSurname());
                    ps.setInt(4, person.getBirthday());
                    return ps;
                },
                keyHolder);
        person.setId(keyHolder.getKey().intValue());

        return person;
    }

    @Override
    public Person read(int id) {
        return jdbcTemplate.query(SQL_FIND_PERSON, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Person> readAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new BeanPropertyRowMapper<Person>(Person.class));
    }

    @Override
    public Person update(int id, Person updatedPerson) {
        jdbcTemplate.update(SQL_UPDATE_PERSON, updatedPerson.getName(), updatedPerson.getPatronymic(), updatedPerson.getSurname(),
                updatedPerson.getBirthday(), id);
        return updatedPerson;
    }

    //reserve method for multiply update; is not used in this version of the task
    public void batchUpdate(List<Person> people) {
        jdbcTemplate.batchUpdate(SQL_UPDATE_PERSON, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, people.get(i).getId());
                ps.setString(2, people.get(i).getName());
                ps.setString(3, people.get(i).getPatronymic());
                ps.setString(4, people.get(i).getSurname());
                ps.setInt(5, people.get(i).getBirthday());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, id);
    }

}
