package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.RestaurantTestData;
import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.korelyakov.restaurant_voting.RestaurantTestData.*;
import static com.korelyakov.restaurant_voting.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantAdminRestControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(RestaurantAdminRestControllerTest.class);

    @Autowired
    private RestaurantAdminRestController controller;

    @Test
    public void get() {
        Restaurant restaurant = controller.get(RESTAURANT_1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.restaurant1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        controller.update(updated, updated.id());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_1_ID), RestaurantTestData.getUpdated());
    }

    @Test
    public void getAll() {
        List<Restaurant> all = controller.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant1, restaurant2, restaurant3);
    }

    @Test
    public void create() {
        Restaurant created = controller.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void delete() {
        controller.delete(RESTAURANT_1_ID);
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_1_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}