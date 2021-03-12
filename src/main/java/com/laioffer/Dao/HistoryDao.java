package com.laioffer.Dao;

import com.laioffer.entity.Item;
import com.laioffer.service.ItemService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class HistoryDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ItemService itemService;

    public void setFavoriteItems(String userId, Item item) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            itemService.saveItem(item);
            session.save(userId, item);
            item.setFavorite(true);
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

    public void unsetFavoriteItems(String userId, String itemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Item item = session.get(Item.class, itemId);
            session.delete(userId, itemId);
            item.setFavorite(false);
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
    public Set<String> getFavoriteItemIds(String userId) {
        Set<String> set = new HashSet<>();
        List<Item> items = new ArrayList<Item>();
        try (Session session = sessionFactory.openSession()) {
            items = session.createCriteria(Item.class, userId).list();
            while (items.iterator().hasNext()) {
                String itemId = items.iterator().next().getId();
                set.add(itemId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;

    }

    public Set<Item> getFavoriteItems(String userId) {
        Set<Item> favoriteItems = null;
        try (Session session = sessionFactory.openSession()) {
            List<Item> items = session.createCriteria(Item.class, userId).list();
            favoriteItems = new HashSet<>(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favoriteItems;
    }
}
