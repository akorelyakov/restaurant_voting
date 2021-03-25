package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepository {
    private final CrudDishRepository crudDishRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public DishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }

    public List<Dish> getAllByRestaurant(int restaurantId) {
        return crudDishRepository.getAllByRestaurant(restaurantId);
    }

    public List<Dish> getAllByRestaurantAndDate(int restaurantId, LocalDate date) {
        return crudDishRepository.getAllByRestaurantAndDate(restaurantId,date );
    }
}
