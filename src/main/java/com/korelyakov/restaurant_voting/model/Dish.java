package com.korelyakov.restaurant_voting.model;

import java.util.Date;

public class Dish extends AbstractNamedEntity {
    private double price;

    private Restaurant restaurant;

    private Date added = new Date();

    public Dish(double price, Restaurant restaurant, Date added) {
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public Dish(Integer id, String name, double price, Restaurant restaurant, Date added) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
}
