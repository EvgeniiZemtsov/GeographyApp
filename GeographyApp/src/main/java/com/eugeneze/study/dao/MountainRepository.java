package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;
import com.eugeneze.models.Mountain;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MountainRepository implements Repository<Mountain> {
    @Override
    public List<Mountain> query(Specification<Mountain> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Mountain> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Mountain mountain) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(mountain);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Mountain mountain) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(mountain);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Mountain mountain = session.get(Mountain.class, id);
        session.delete(mountain);

        session.getTransaction().commit();
        session.close();
    }
}
