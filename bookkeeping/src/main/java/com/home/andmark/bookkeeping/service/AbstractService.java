package com.home.andmark.bookkeeping.service;

import java.util.List;

public interface AbstractService<T> {

    void save(T t);

    T findOne(int id);

    List<T> findAll();

    void update(int id, T t);

    void delete(int id);

}
