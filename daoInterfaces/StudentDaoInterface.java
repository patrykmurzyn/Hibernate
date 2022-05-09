package com.hello.daoInterfaces;

import com.hello.entity.Student;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public interface StudentDaoInterface<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public T findByName(String imie, String nazwisko);

    public void delete(T entity);

    public List<T> findAll();

    public List<T> findAllFromClass(Long index);

    public List<T> SortAlphabetical(Long index);

    public List<T> SortByPoints(Long index);

    public List<T> SearchPartial(Long index, String text);

    public List<T> SearchByStatus(Long index, String status);
}
