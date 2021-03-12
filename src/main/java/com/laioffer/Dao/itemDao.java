package com.laioffer.Dao;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class itemDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveItem(Item item) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<String> getKeywords(String itemId) {
        List<String> kw = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            kw = session.get(Item.class, itemId).getKeywords();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kw;
    }

}

