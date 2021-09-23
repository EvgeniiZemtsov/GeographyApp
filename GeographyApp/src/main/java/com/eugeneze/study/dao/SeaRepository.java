package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;
import com.eugeneze.models.Sea;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeaRepository implements Repository<Sea>{
    @Override
    public List<Sea> query(Specification<Sea> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Sea> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Sea sea) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(sea);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Sea sea) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(sea);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Sea sea = session.get(Sea.class, id);
        session.delete(sea);

        session.getTransaction().commit();
        session.close();
    }
}
