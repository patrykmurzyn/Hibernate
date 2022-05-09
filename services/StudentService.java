package com.hello.services;

import java.util.List;

import com.hello.daos.StudentDao;
import com.hello.entity.Student;


import javax.persistence.Query;

import static com.hello.HibernateUtil.getSessionFactory;

public class StudentService {

    private static StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDao();
    }

    public void persist(Student entity) {
        studentDao.openCurrentSessionwithTransaction();
        studentDao.persist(entity);
        studentDao.closeCurrentSessionwithTransaction();
    }

    public void update(Student entity) {
        studentDao.openCurrentSessionwithTransaction();
        studentDao.update(entity);
        studentDao.closeCurrentSessionwithTransaction();
    }

    public Student findById(Long id) {
        studentDao.openCurrentSession();
        Student student = studentDao.findById(id);
        studentDao.closeCurrentSession();
        return student;
    }

    public Student findByName(String imie, String nazwisko) {
        studentDao.openCurrentSession();
        Student student = studentDao.findByName(imie, nazwisko);
        studentDao.closeCurrentSession();
        return student;
    }

    public void delete(Long id) {
        studentDao.openCurrentSessionwithTransaction();
        Student student = studentDao.findById(id);
        studentDao.delete(student);
        studentDao.closeCurrentSessionwithTransaction();
    }

    public List<Student> findAll() {
            studentDao.openCurrentSession();
            List<Student> students = studentDao.findAll();
            studentDao.closeCurrentSession();
            return students;
    }

    public List<Student> findAllFromClass(Long index) {
        studentDao.openCurrentSession();
        List<Student> students = studentDao.findAllFromClass(index);
        studentDao.closeCurrentSession();
        return students;
    }

    public List<Student> SortAlphabetical(Long index) {
        studentDao().openCurrentSession();
        List<Student> students = studentDao().SortAlphabetical(index);
        studentDao.closeCurrentSession();
        return students;
    }

    public List<Student> SortByPoints(Long index) {
        studentDao().openCurrentSession();
        List<Student> students = studentDao().SortByPoints(index);
        studentDao.closeCurrentSession();
        return students;
    }

    public List<Student> SearchPartial(Long index, String text) {
        studentDao().openCurrentSession();
        List<Student> students = studentDao().SearchPartial(index, text);
        studentDao.closeCurrentSession();
        return students;
    }

    public List<Student> SearchByStatus(Long index, String status) {
        studentDao().openCurrentSession();
        List<Student> students = studentDao().SearchPartial(index, status);
        studentDao.closeCurrentSession();
        return students;
    }

    public StudentDao studentDao() {
        return studentDao;
    }
}
