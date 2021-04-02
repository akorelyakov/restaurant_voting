package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.RestaurantTestData;
import com.korelyakov.restaurant_voting.UserTestData;
import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.model.User;
import com.korelyakov.restaurant_voting.util.exception.NotFoundException;
import com.korelyakov.restaurant_voting.web.AbstractControllerTest;
import com.korelyakov.restaurant_voting.web.json.JsonUtil;
import com.korelyakov.restaurant_voting.web.user.AdminRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.korelyakov.restaurant_voting.RestaurantTestData.*;
import static com.korelyakov.restaurant_voting.TestUtil.readFromJson;
import static com.korelyakov.restaurant_voting.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantAdminRestControllerTest extends AbstractControllerTest {
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

//
//    private static final String REST_URL = AdminRestController.REST_URL + '/';
//
//    @Autowired
//    private AdminRestController controller;
//
//    @org.junit.jupiter.api.Test
//    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID))
//                .andExpect(status().isOk())
//                .andDo(print())
//                // https://jira.spring.io/browse/SPR-14472
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(admin));
//    }
//
//    @org.junit.jupiter.api.Test
//    void getByEmail() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + "by?email=" + user.getEmail()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(user));
//    }
//
//    @org.junit.jupiter.api.Test
//    void delete() throws Exception {
//        perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//        assertThrows(NotFoundException.class, () -> controller.get(USER_ID));
//    }
//
//    @org.junit.jupiter.api.Test
//    void update() throws Exception {
//        User updated = UserTestData.getUpdated();
//        perform(MockMvcRequestBuilders.put(REST_URL + USER_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(updated)))
//                .andExpect(status().isNoContent());
//
//        USER_MATCHER.assertMatch(controller.get(USER_ID), updated);
//    }
//
//    @org.junit.jupiter.api.Test
//    void createWithLocation() throws Exception {
//        User newUser = UserTestData.getNew();
//        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(newUser)))
//                .andExpect(status().isCreated());
//
//        User created = readFromJson(action, User.class);
//        int newId = created.id();
//        newUser.setId(newId);
//        USER_MATCHER.assertMatch(created, newUser);
//        USER_MATCHER.assertMatch(controller.get(newId), newUser);
//    }
//
//    @org.junit.jupiter.api.Test
//    void getAll() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_MATCHER.contentJson(admin, user));
//    }
}