package com.laioffer.service;

import com.laioffer.Dao.HistoryDao;
import com.laioffer.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HistoryService {

    @Autowired
    private HistoryDao historyDao;


    public void unsetFavoriteItems(String userId, String itemId) {
        historyDao.unsetFavoriteItems(userId, itemId);
    }
    public void setFavoriteItems(String userId, Item item) {
        historyDao.setFavoriteItems(userId, item);
    }
    public Set<String> getFavoriteItemIds(String userId) {
        return historyDao.getFavoriteItemIds(userId);
    }
    public Set<Item> getFavoriteItems(String userId) {
        return historyDao.getFavoriteItems(userId);
    }


}
