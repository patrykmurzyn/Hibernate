package com.hello.daoInterfaces;

import java.io.Serializable;
import java.util.List;

public interface ClassDaoInterface<T, Id extends Serializable>{

    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public T findByName(String name);

    public void delete(T entity);

    public List<T> findAll();
}
