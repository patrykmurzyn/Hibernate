package com.hello.services;

import com.hello.daos.ClassDao;
import com.hello.daos.StudentDao;
import com.hello.entity.Class;
import com.hello.entity.Student;

import java.util.List;

public class ClassService {

    private static ClassDao classDao;

    public ClassService() {
        classDao = new ClassDao();
    }

    public void persist(Class entity) {
        classDao.openCurrentSessionwithTransaction();
        classDao.persist(entity);
        classDao.closeCurrentSessionwithTransaction();
    }

    public void update(Class entity) {
        classDao.openCurrentSessionwithTransaction();
        classDao.update(entity);
        classDao.closeCurrentSessionwithTransaction();
    }

    public Class findById(Long id) {
        classDao.openCurrentSession();
        Class c = classDao.findById(id);
        classDao.closeCurrentSession();
        return c;
    }

    public Class findByName(String name) {
        classDao().openCurrentSession();
        Class c = classDao().findByName(name);
        classDao().closeCurrentSession();
        return c;
    }

    public List<Class> findAll() {
        classDao.openCurrentSession();
        List<Class> c = classDao.findAll();
        classDao.closeCurrentSession();
        return c;
    }

    public void delete(Long id) {
        classDao.openCurrentSessionwithTransaction();
        Class c = classDao.findById(id);
        classDao.delete(c);
        classDao.closeCurrentSessionwithTransaction();
    }

    public ClassDao classDao() {
        return classDao;
    }
}
