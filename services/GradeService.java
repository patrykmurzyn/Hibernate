package com.hello.services;

import com.hello.daos.GradeDao;
import com.hello.entity.Grade;
import com.hello.entity.Student;

import java.util.List;

public class GradeService {
    private static GradeDao gradeDao;

    public GradeService() {
        gradeDao = new GradeDao();
    }

    public void persist(Grade entity) {
        gradeDao.openCurrentSessionwithTransaction();
        gradeDao.persist(entity);
        gradeDao.closeCurrentSessionwithTransaction();
    }

    public void update(Grade entity) {
        gradeDao.openCurrentSessionwithTransaction();
        gradeDao.update(entity);
        gradeDao.closeCurrentSessionwithTransaction();
    }

    public Grade findById(Long id) {
        gradeDao.openCurrentSession();
        Grade grade = gradeDao.findById(id);
        gradeDao.closeCurrentSession();
        return grade;
    }

    public List<Grade> findAll() {
        gradeDao.openCurrentSession();
        List<Grade> grades = gradeDao.findAll();
        gradeDao.closeCurrentSession();
        return grades;
    }

    public void delete(Long id) {
        gradeDao.openCurrentSessionwithTransaction();
        Grade grade = gradeDao.findById(id);
        gradeDao.delete(grade);
        gradeDao.closeCurrentSessionwithTransaction();
    }

    public GradeDao gradeDao() {
        return gradeDao;
    }
}
