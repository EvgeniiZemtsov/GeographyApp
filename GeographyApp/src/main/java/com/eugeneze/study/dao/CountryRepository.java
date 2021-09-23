package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;
import com.eugeneze.models.*;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryRepository implements Repository<Country> {

    @Override
    public List<Country> query(Specification<Country> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Country> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Country country) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.saveOrUpdate(country);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Country country) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(country);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Country country = session.get(Country.class, id);
        session.delete(country);

        session.getTransaction().commit();
        session.close();
    }

}
