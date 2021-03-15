package com.korelyakov.restaurant_voting.model;

import java.util.Date;

public class Dish extends AbstractNamedEntity {
    private int price;

    private Restaurant restaurant;

    private User user;

    private Date added = new Date();

    public Dish(int price, Restaurant restaurant, Date added) {
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant, Date added) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
