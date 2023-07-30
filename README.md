# spring-studyworks
coursework while learning the framework spring
---
1. BookKeeping - program for digital record keeping in the library (3 realisations: jdbcTemplate, Hibernate, Data JPA)
2. will be
---
# BookKeeping
##Terms of Reference

*Task*

The local library wants to go digital for books. You need to implement a web application for them. The librarians need to be able to register readers, issue them with books and release books (after the reader returns the book back to the library).

*Technologies used*

Java, Spring Framework

Spring Data JPA, Hibernate, JDBC Template, PostgreSQL, H2,

JUnit, Mockito

Hibernate Validator, Jakarta Validation,

Thymeleaf, Bootstrap

Overall, web application utilizes a modern Java Spring stack with Thymeleaf for templating and PostgreSQL as the database. It ensures data integrity and validation through the use of Hibernate Validator, and testing is facilitated using JUnit 5 and Mockito. Additionally, Bootstrap provides a clean and responsive user interface, enhancing the overall user experience.

*Branch dev-jdbctemplate - realized with Spring mvc and JdbcTemplate and cover tests*

*Branch dev-hibernate - realized with Spring mvc and Hibernate*

*Branch main - realized with Spring mvc and Spring Data JPA, enhanced functionality*

# Main features
- implementation of all CRUD operations
- a page with a list of all people (people are clickable - when you click on them, the page of the person is go to the person's page)
- a page with the list of all books (books are clickable - when you click, you go to the page of the book)
- a person's page that shows their field values and a list of books they've borrowed
- a page of a book that shows the field values of that book and the name of the person, who picked up the book
- on the book page, if the book is taken by a person, there should be a button next to their name "Release Book"
- on the book page, if the book is free, there should be a drop-down list (<select>) with all people and a "Assign book" button
- added pagination for books
- added sorting of books by year
- a page for searching books by title and matching initial letters
- added an automatic check to see if a person is overdue to return a book

