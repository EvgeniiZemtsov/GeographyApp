package com.eugeneze.dao;

import com.eugeneze.dao.specifications.Specification;
import com.eugeneze.models.City;
import com.eugeneze.models.Language;
import com.eugeneze.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LanguageRepository implements Repository<Language> {

    @Override
    public List<Language> query(Specification<Language> specification) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();
        Query query = session.createQuery(specification.getHqlQuery());
        List<Language> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

    @Override
    public void create(Language language) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.save(language);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Language language) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        session.update(language);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.getTransaction().begin();

        Language language = session.get(Language.class, id);
        session.delete(language);

        session.getTransaction().commit();
        session.close();
    }
}
