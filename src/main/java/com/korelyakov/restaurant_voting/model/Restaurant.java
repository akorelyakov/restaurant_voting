package com.korelyakov.restaurant_voting.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {
    private List<Dish> menu;

    private User user;

    public Restaurant(String name, List<Dish> menu, User user) {
        this.name = name;
        this.menu = menu;
        this.user = user;
    }

    public Restaurant(Integer id, String name, List<Dish> menu, User user) {
        super(id, name);
        this.name = name;
        this.menu = menu;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
