package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final CrudRestaurantRepository crudRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public Restaurant getWithMenu(int id) {
        return crudRepository.getWithMenu(id);
    }

    public List<Restaurant> getWithMenuByDate(int id, LocalDate date) {
        return crudRepository.getAllWithMenusByDate(id, date);
    }

    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    public List<Restaurant> getAllWithMenu() {
        return crudRepository.getAllWithMenus();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate date) {
        return crudRepository.getAllWithMenusByDate(date);
    }
}
