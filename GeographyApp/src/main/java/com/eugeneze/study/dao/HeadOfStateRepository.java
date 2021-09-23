package com.eugeneze.study.dao;

import com.eugeneze.study.dao.specifications.Specification;
import com.eugeneze.models.HeadOfState;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeadOfStateRepository implements Repository<HeadOfState> {

    @Override
    public List<HeadOfState> query(Specification<HeadOfState> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<HeadOfState> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(HeadOfState headOfState) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(headOfState);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(HeadOfState headOfState) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(headOfState);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        HeadOfState headOfState = session.get(HeadOfState.class, id);
        session.delete(headOfState);

        session.getTransaction().commit();
        session.close();
    }
}
