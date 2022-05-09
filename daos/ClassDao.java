package com.hello.daos;

import com.hello.daoInterfaces.ClassDaoInterface;
import com.hello.entity.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class ClassDao implements ClassDaoInterface<Class, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ClassDao() {
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
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Class.class);
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

    public void persist(Class entity) {
        getCurrentSession().save(entity);
    }

    public void update(Class entity) {
        getCurrentSession().update(entity);
    }

    public Class findById(Long id) {
        Class c = (Class) getCurrentSession().get(Class.class, id);
        return c;
    }

    public Class findByName(String name) {
        Query query = getCurrentSession().createQuery("from Class where nazwa_grupy = :value");
        query.setParameter("value", name);
        Class c = (Class) query.getSingleResult();
        return c;
    }

    public void delete(Class entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Class> findAll() {
        List<Class> c = (List<Class>) getCurrentSession().createQuery("from Class").list();
        return c;
    }
}
