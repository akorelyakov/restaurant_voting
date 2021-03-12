package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.repository.RestaurantRepository;
import com.korelyakov.restaurant_voting.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.korelyakov.restaurant_voting.util.ValidationUtil.*;

public class RestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    public RestaurantRestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        return repository.save(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, id);
        log.info("update {} for user {}", restaurant, userId);
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return repository.getAll(userId);
    }
}
