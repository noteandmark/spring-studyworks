package com.home.andmark.bookkeeping.dao;

import java.util.List;

public interface AbstractDAO<T> {

    T save(T t);

    T read(int id);

    List<T> readAll();

    T update(int id, T t);

    int delete(int id);

}

