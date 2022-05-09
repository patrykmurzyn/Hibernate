package com.hello.daos;

import com.hello.daoInterfaces.GradeDaoInterface;
import com.hello.entity.Grade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class GradeDao implements GradeDaoInterface<Grade, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public GradeDao() {
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
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Grade.class);
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

    public void persist(Grade entity) {
        getCurrentSession().save(entity);
    }

    public void update(Grade entity) {
        getCurrentSession().update(entity);
    }

    public Grade findById(Long id) {
        Grade grade = (Grade) getCurrentSession().get(Grade.class, id);
        return grade;
    }

    public void delete(Grade entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Grade> findAll() {
        List<Grade> grades = (List<Grade>) getCurrentSession().createQuery("from Grade").list();
        return grades;
    }
}
