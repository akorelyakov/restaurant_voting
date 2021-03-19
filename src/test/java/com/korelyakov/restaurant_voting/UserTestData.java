package com.korelyakov.restaurant_voting;


import com.korelyakov.restaurant_voting.model.Role;
import com.korelyakov.restaurant_voting.model.User;
import com.korelyakov.restaurant_voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static com.korelyakov.restaurant_voting.model.AbstractBaseEntity.START_SEQ;


public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class,
            "registered", "password", "roles", "votes");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
