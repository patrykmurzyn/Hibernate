package com.hello;
import com.hello.entity.Class;
import com.hello.entity.Grade;
import com.hello.entity.Student;

import org.hibernate.Session;

public class HibernateRepository {

    public static void AddStudent(Student s) {
        final Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(s);
        session.getTransaction().commit();
        session.close();
    }


    public static void AddClass(Class c) {
        final Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    public static void AddGrade(Grade g) {
        final Session session =  HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(g);
        session.getTransaction().commit();
        session.close();
    }
}
