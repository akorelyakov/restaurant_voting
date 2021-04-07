package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.RestaurantTestData;
import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.util.exception.NotFoundException;
import com.korelyakov.restaurant_voting.web.AbstractControllerTest;
import com.korelyakov.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.korelyakov.restaurant_voting.RestaurantTestData.*;
import static com.korelyakov.restaurant_voting.TestUtil.readFromJson;
import static com.korelyakov.restaurant_voting.TestUtil.userHttpBasic;
import static com.korelyakov.restaurant_voting.UserTestData.NOT_FOUND;
import static com.korelyakov.restaurant_voting.UserTestData.admin;
import static com.korelyakov.restaurant_voting.web.restaurant.RestaurantAdminRestController.REST_URL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(RestaurantRestControllerTest.class);

    @Autowired
    private RestaurantAdminRestController controller;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + RESTAURANT_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurant1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + RESTAURANT_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + RESTAURANT_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_1_ID), RestaurantTestData.getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
                .with(userHttpBasic(admin)));
        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurant2, restaurant1, restaurant3));
    }
}