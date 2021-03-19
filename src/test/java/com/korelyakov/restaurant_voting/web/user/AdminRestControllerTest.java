package com.korelyakov.restaurant_voting.web.user;

import com.korelyakov.restaurant_voting.UserTestData;
import com.korelyakov.restaurant_voting.model.User;
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

import static com.korelyakov.restaurant_voting.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AdminRestControllerTest {
    protected static final Logger log = LoggerFactory.getLogger(AdminRestControllerTest.class);

    @Autowired
    private AdminRestController controller;

    @Test
    public void get() {
        User user = controller.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = controller.getByMail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        controller.update(updated, updated.id());
        USER_MATCHER.assertMatch(controller.get(USER_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = controller.getAll();
        USER_MATCHER.assertMatch(all, admin, user);
    }

    @Test
    public void create() {
        User created = controller.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(controller.get(newId), newUser);
    }

    @Test
    public void delete() {
        controller.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> controller.get(USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

}