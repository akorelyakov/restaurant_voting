package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.repository.DishRepository;
import com.korelyakov.restaurant_voting.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

import static com.korelyakov.restaurant_voting.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;

    @Autowired
    protected DishRepository dishRepository;

    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public Restaurant getWithDishes(int id, @Nullable LocalDate date) {
        if (date == null) {
            log.info("getWithDishes with id={}", id);
            return repository.getWithDishes(id);
        }
        log.info("getWithDishesByDate={} with id={}", date, id);
        return repository.getWithDishesByDate(id, date);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        log.info("getAllWithDishesByDate={}", date);
        return repository.getAllWithDishesByDate(date);
    }
}
