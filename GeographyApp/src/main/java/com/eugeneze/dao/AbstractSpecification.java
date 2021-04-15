package com.eugeneze.dao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract public class AbstractSpecification<T> implements Specification<T> {

    @Override
    public boolean isSatisfiedBy(T t) {
        throw new NotImplementedException();
    }
}
