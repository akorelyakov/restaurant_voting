package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if updated restaurant do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if restaurant do not belong to userId
    boolean delete(int id, int userId);

    // null if restaurant do not belong to userId
    Restaurant get(int id, int userId);

    // ORDERED name asc
    List<Restaurant> getAll(int userId);

    // ORDERED name asc
    List<Restaurant> getAllWithMenu();
}
