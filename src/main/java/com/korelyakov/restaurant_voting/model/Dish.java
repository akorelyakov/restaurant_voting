package com.korelyakov.restaurant_voting.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static java.time.LocalDate.now;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 50_000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate added = now();

    public Dish() {
    }

    public Dish(Integer id, String name, int price, LocalDate added, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.restaurant = restaurant;
    }

    public Dish(String name, int price, Restaurant restaurant) {
        this(null, name, price, now(), restaurant);
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

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "Dish{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", restaurant=" + restaurant +
               ", added=" + added +
               '}';
    }
}
