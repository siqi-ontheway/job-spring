package com.laioffer.service;

import java.util.List;

import com.laioffer.Dao.itemDao;
import com.laioffer.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    @Autowired
    private itemDao itemDao;


    public void saveItem(Item item) {
        itemDao.saveItem(item);
    }
    public List<String> getKeywords(String itemId) {
        return itemDao.getKeywords(itemId);
    }


}

