package com.laioffer.service;

import com.laioffer.Dao.userDao;
import com.laioffer.entity.users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private userDao userDao;

    public void addUser(users user) {

        userDao.addUser(user);
    }
    public String getFullname(String userId) {
        return userDao.getFullname(userId);
    }
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

}

