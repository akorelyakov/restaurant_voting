package com.korelyakov.restaurant_voting.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "dish",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "added"},
                name = "restaurant_name_idx"))
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 50_000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date added = new Date();

    public Dish() {
    }

    public Dish(int price, Restaurant restaurant) {
        this(price, restaurant, new Date());
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant) {
        this(id, name, price, restaurant, new Date());
    }

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

    @Override
    public String toString() {
        return "Dish{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", restaurant=" + restaurant +
               '}';
    }
}
