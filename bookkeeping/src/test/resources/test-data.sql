INSERT INTO person (name, patronymic, surname, birthday) VALUES ('Petr', 'Stepanovic', 'Popov', 1980);
INSERT INTO person (name, patronymic, surname, birthday) VALUES ('Alexandr', 'Vasiljevic', 'Skvorzov', 1970);
INSERT INTO person (name, patronymic, surname, birthday) VALUES ('Yurij', 'Yurjevich', 'Ivanov', 2001);
INSERT INTO person (name, patronymic, surname, birthday) VALUES ('Stepan', 'Pavlovich', 'Dubov', 1985);

INSERT INTO book (person_id, title, author, year) VALUES (null,'Robinson Crusoe','Daniel Defoe',1719);
INSERT INTO book (person_id, title, author, year) VALUES (2,'Gulliver’s Travels','Jonathan Swift',1726);
INSERT INTO book (person_id, title, author, year) VALUES (3,'Emma','Jane Austen',1816);
INSERT INTO book (person_id, title, author, year) VALUES (null,'Frankenstein','Mary Shelley',1818);