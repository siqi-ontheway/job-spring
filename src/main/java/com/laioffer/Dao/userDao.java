package com.laioffer.Dao;

import com.laioffer.entity.Item;
import com.laioffer.entity.users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class userDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void addUser(users user) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public String getFullname(String userId) {
        users user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session.get(users.class, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (user != null) {
            return user.getFirstName() + " " + user.getLastName();
        }

        return null;
    }

    public void deleteUser(String userId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            users user = session.get(users.class, userId);
            session.delete(user);
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
}


