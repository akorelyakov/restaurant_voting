package com.korelyakov.restaurant_voting.web;

import com.korelyakov.restaurant_voting.model.AbstractBaseEntity;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}