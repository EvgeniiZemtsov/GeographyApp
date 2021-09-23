package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;
import com.eugeneze.models.Ocean;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OceanRepository implements Repository<Ocean>{
    @Override
    public List<Ocean> query(Specification<Ocean> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Ocean> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Ocean ocean) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(ocean);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Ocean ocean) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(ocean);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Ocean ocean = session.get(Ocean.class, id);
        session.delete(ocean);

        session.getTransaction().commit();
        session.close();
    }
}
