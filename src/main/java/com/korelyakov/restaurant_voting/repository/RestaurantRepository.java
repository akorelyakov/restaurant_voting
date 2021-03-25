package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return crudRestaurantRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    // without dishes
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    public Restaurant getWithDishes(int id) {
        return crudRestaurantRepository.getWithDishes(id);
    }

    public List<Restaurant> getWithDishesByDate(int id, LocalDate date) {
        return crudRestaurantRepository.getWithDishesByDate(id, date);
    }

    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        return crudRestaurantRepository.getAllWithDishesByDate(date);
    }
}
