package com.korelyakov.restaurant_voting.web.user;

import com.korelyakov.restaurant_voting.model.User;

import static com.korelyakov.restaurant_voting.web.SecurityUtil.authUserId;

public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}