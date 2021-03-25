package com.korelyakov.restaurant_voting.web.dish;

import com.korelyakov.restaurant_voting.model.Dish;
import com.korelyakov.restaurant_voting.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.korelyakov.restaurant_voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController {
    static final String REST_URL = "/rest/admin/dish";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    public DishAdminRestController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish get(int id) {
        log.info("get dish with id={}", id);
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    public List<Dish> getAll() {
        log.info("getAll");
        return dishRepository.getAll();
    }

    public void create(Dish dish, int restaurantId) {
        log.info("save dish {} to restaurant with id={}", dish, restaurantId);
        checkNew(dish);
        dishRepository.save(dish, restaurantId);
    }

    public void update(Dish dish, int id, int restaurantId) {
        log.info("update dish {} with id={} to restaurant with id={}", dish, id, restaurantId);
        assureIdConsistent(dish, id);
        checkNotFoundWithId(dishRepository.save(dish, restaurantId), dish.getId());
    }

    public void delete(int id) {
        log.info("delete dish with id={}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }
}
