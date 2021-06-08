package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;
import com.eugeneze.models.City;
import com.eugeneze.models.Currency;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CurrencyRepository implements Repository<Currency> {

    @Override
    public List<Currency> query(Specification<Currency> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Currency> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Currency currency) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(currency);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Currency currency) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(currency);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Currency currency = session.get(Currency.class, id);
        session.delete(currency);

        session.getTransaction().commit();
        session.close();
    }
}
