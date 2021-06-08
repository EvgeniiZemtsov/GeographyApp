package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;
import com.eugeneze.models.City;
import com.eugeneze.models.Sight;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SightRepository implements Repository<Sight>{
    @Override
    public List<Sight> query(Specification<Sight> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Sight> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Sight sight) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(sight);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Sight sight) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(sight);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Sight sight = session.get(Sight.class, id);
        session.delete(sight);

        session.getTransaction().commit();
        session.close();
    }
}
