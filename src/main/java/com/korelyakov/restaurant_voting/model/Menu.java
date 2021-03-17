package com.korelyakov.restaurant_voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate added;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(@NotNull Restaurant restaurant, @NotNull LocalDate added, Set<Dish> dishes) {
        this(null, restaurant, added, dishes);
    }

    public Menu(Integer id, @NotNull Restaurant restaurant, @NotNull LocalDate added, Set<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.added = added;
        this.dishes = dishes;
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

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return restaurant.equals(menu.restaurant) && added.equals(menu.added) && Objects.equals(dishes, menu.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), restaurant, added, dishes);
    }

    @Override
    public String toString() {
        return "Menu{" +
               "id=" + id +
               ", restaurant=" + restaurant +
               ", added=" + added +
               ", dishes=" + dishes +
               '}';
    }
}
