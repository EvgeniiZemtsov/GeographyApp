package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;
import com.eugeneze.models.City;
import com.eugeneze.models.Continent;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ContinentRepository implements Repository<Continent>{
    @Override
    public List<Continent> query(Specification<Continent> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Continent> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Continent continent) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(continent);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Continent continent) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(continent);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Continent continent = session.get(Continent.class, id);
        session.delete(continent);

        session.getTransaction().commit();
        session.close();
    }
}
