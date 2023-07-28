package com.home.andmark.bookkeeping.dao;

import java.util.List;

public interface AbstractDAO<T> {

    void save(T t);

    T read(int id);

    List<T> readAll();

    void update(int id, T t);

    void delete(int id);

}

