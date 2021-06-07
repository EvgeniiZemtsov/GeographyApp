package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;
import com.eugeneze.models.City;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;

public class CityRepository implements Repository<City> {

    @Override
    public List<City> query(Specification<City> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<City> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }


    @Override
    public void create(City city) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(city);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(City city) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(city);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        City city = session.get(City.class, id);
        session.delete(city);

        session.getTransaction().commit();
        session.close();
    }
}
