package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;

import java.util.List;

public interface Repository<T> {

    List<T> query(Specification<T> specification);

    void create(T t);
    void update(T t);
    void delete(int id);
}
