package com.korelyakov.restaurant_voting.web.vote;

import com.korelyakov.restaurant_voting.DishTestData;
import com.korelyakov.restaurant_voting.model.Dish;
import com.korelyakov.restaurant_voting.util.exception.NotFoundException;
import com.korelyakov.restaurant_voting.web.AbstractControllerTest;
import com.korelyakov.restaurant_voting.web.dish.DishAdminRestController;
import com.korelyakov.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.korelyakov.restaurant_voting.DishTestData.*;
import static com.korelyakov.restaurant_voting.RestaurantTestData.RESTAURANT_1_ID;
import static com.korelyakov.restaurant_voting.RestaurantTestData.RESTAURANT_2_ID;
import static com.korelyakov.restaurant_voting.TestUtil.readFromJson;
import static com.korelyakov.restaurant_voting.TestUtil.userHttpBasic;
import static com.korelyakov.restaurant_voting.UserTestData.NOT_FOUND;
import static com.korelyakov.restaurant_voting.UserTestData.admin;
import static com.korelyakov.restaurant_voting.web.vote.VoteRestController.REST_URL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(VoteRestControllerTest.class);

    @Autowired
    private DishAdminRestController controller;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + DISH_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + DISH_1_ID))
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
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + DISH_1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(DISH_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + DISH_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(controller.get(DISH_1_ID), DishTestData.getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
                .with(userHttpBasic(admin)));
        Dish created = readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(controller.get(newId), newDish);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/rest/admin/restaurants/" + RESTAURANT_2_ID + "/dishes")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish3, dish4, dish5));
    }
}