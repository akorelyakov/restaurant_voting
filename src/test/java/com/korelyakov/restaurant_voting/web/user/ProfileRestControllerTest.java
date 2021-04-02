package com.korelyakov.restaurant_voting.web.user;

import com.korelyakov.restaurant_voting.model.Role;
import com.korelyakov.restaurant_voting.model.User;
import com.korelyakov.restaurant_voting.web.AbstractControllerTest;
import com.korelyakov.restaurant_voting.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.korelyakov.restaurant_voting.TestUtil.readFromJson;
import static com.korelyakov.restaurant_voting.TestUtil.userHttpBasic;
import static com.korelyakov.restaurant_voting.UserTestData.*;
import static com.korelyakov.restaurant_voting.web.user.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ProfileRestController controller;

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(controller.getAll(), admin);
    }

    @Test
    void update() throws Exception {
        User updated = new User(null, "newName", "user@yandex.ru", "newPassword", Role.USER);
        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(controller.get(USER_ID), updated);
    }

//    @Test
//    void getWithMeals() throws Exception {
//        assumeDataJpa();
//        perform(MockMvcRequestBuilders.get(REST_URL + "/with-meals")
//                .with(userHttpBasic(user)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(USER_WITH_MEALS_MATCHER.contentJson(user));
//    }
//
//    @Test
//    void registerInvalid() throws Exception {
//        UserTo newTo = new UserTo(null, null, null, null, 1);
//        perform(MockMvcRequestBuilders.post(REST_URL + "/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(newTo)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR));
//    }
//
//    @Test
//    void updateInvalid() throws Exception {
//        UserTo updatedTo = new UserTo(null, null, "password", null, 1500);
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR));
//    }
//
//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    void updateDuplicate() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "admin@gmail.com", "newPassword", 1500);
//
//        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR))
//                .andExpect(detailMessage(EXCEPTION_DUPLICATE_EMAIL));
//    }
}