package com.laioffer.entity;

import com.laioffer.entity.Item;
import com.laioffer.entity.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "history")
public class history implements Serializable {

    private static final long serialVersionUID = -6571020025726257848L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private users users;

    @ManyToOne
    private Item item;
    private Timestamp last_favor_time;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public users getUser() {
        return users;
    }

    public users setUser() {
        return users;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Timestamp getLast_favor_time() {
        return last_favor_time;
    }

    public void setLast_favor_time(Timestamp last_favor_time) {
        this.last_favor_time = last_favor_time;
    }
}

