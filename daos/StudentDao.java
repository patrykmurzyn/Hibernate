package com.hello.daos;

import java.util.List;
import com.hello.daoInterfaces.StudentDaoInterface;
import com.hello.entity.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.hello.entity.Student;
import javax.persistence.Query;

public class StudentDao implements StudentDaoInterface<Student, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public StudentDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Student entity) {
        getCurrentSession().save(entity);
    }

    public void update(Student entity) {
        getCurrentSession().update(entity);
    }

    public Student findById(Long id) {
        Student student = (Student) getCurrentSession().get(Student.class, id);
        return student;
    }

    public Student findByName(String imie, String nazwisko) {
        Query query = getCurrentSession().createQuery("from Student where imie = :value1 and nazwisko = :value2");
        query.setParameter("value1", imie);
        query.setParameter("value2", nazwisko);
        Student student = (Student) query.getSingleResult();
        return student;
    }

    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        List<Student> students = (List<Student>) getCurrentSession().createQuery("from Student").list();
        return students;
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAllFromClass(Long index) {
        Query query = getCurrentSession().createQuery("from Student where class_id = :value");
        query.setParameter("value", index);

        List<Student> students = (List<Student>) query.getResultList();

        return students;
    }

    public List<Student> SortAlphabetical(Long index) {
        Query query = getCurrentSession().createQuery("from Student where class_id = :value order by nazwisko");
        query.setParameter("value", index);

        List<Student> students = (List<Student>) query.getResultList();
        return students;
    }

    public List<Student> SortByPoints(Long index) {
        Query query = getCurrentSession().createQuery("from Student where class_id = :value order by ilosc_punktow");
        query.setParameter("value", index);

        List<Student> students = (List<Student>) query.getResultList();
        return students;
    }

    public List<Student> SearchPartial(Long index, String text) {
        Query query = getCurrentSession().createQuery("from Student where class_id = :value1 AND nazwisko Like %:value2%");
        query.setParameter("value1", index);
        query.setParameter("value2", text);

        List<Student> students = (List<Student>) query.getResultList();
        return students;
    }

    public List<Student> SearchByStatus(Long index, String status) {
        Query query = getCurrentSession().createQuery("from Student where class_id = :value1 AND status = :value2");
        query.setParameter("value1", index);
        query.setParameter("value2", status);

        List<Student> students = (List<Student>) query.getResultList();
        return students;
    }

}

//https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-jpa-dao-example/