package com.korelyakov.restaurant_voting.model;

import java.util.Date;

public class Vote extends AbstractBaseEntity {
    private final Restaurant restaurant;

    private final User user;

    private final Date voted = new Date();

    public Vote(Integer id, Restaurant restaurant, User user) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Date getVoted() {
        return voted;
    }
}
