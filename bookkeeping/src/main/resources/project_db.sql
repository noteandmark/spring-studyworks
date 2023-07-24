CREATE TABLE IF NOT EXISTS person
(
    id         int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       varchar(100) NOT NULL,
    patronymic varchar(100) NOT NULL,
    surname    varchar(100) NOT NULL,
    birthday   int check (birthday > 1900)
);

CREATE TABLE IF NOT EXISTS book
(
    id        int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    person_id int          REFERENCES person (id) ON DELETE SET NULL,
    title     varchar(200) NOT NULL,
    author    varchar(200) NOT NULL,
    year      int check (year > 0)
);