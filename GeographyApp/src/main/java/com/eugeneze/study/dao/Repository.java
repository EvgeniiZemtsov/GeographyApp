package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;

import java.util.List;

public interface Repository<T> {

    List<T> query(Specification<T> specification);

    void create(T t);
    void update(T t);
    void delete(int id);
}
