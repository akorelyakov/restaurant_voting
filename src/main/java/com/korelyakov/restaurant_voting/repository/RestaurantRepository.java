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

    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    // without dishes
    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    public Restaurant getWithDishes(int id) {
        return crudRepository.getWithDishes(id);
    }

    public List<Restaurant> getWithDishesByDate(int id, LocalDate date) {
        return crudRepository.getWithDishesByDate(id, date);
    }

    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        return crudRepository.getAllWithDishesByDate(date);
    }
}
