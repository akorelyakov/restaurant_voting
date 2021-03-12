package com.korelyakov.restaurant_voting.model;

import java.util.Date;
import java.util.List;

public class Restaurant extends AbstractNamedEntity {
    private List<Dish> menu;

    private User user;

    private boolean enabled = true;

    private Date registered = new Date();

    public Restaurant(String name, List<Dish> menu, User user, boolean enabled, Date registered) {
        this.name = name;
        this.menu = menu;
        this.user = user;
        this.enabled = enabled;
        this.registered = registered;
    }

    public Restaurant(Integer id, String name, List<Dish> menu, User user, boolean enabled, Date registered) {
        super(id, name);
        this.name = name;
        this.menu = menu;
        this.user = user;
        this.enabled = enabled;
        this.registered = registered;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
