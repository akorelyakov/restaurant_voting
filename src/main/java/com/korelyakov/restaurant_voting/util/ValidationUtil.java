package com.korelyakov.restaurant_voting.util;

import com.korelyakov.restaurant_voting.model.AbstractBaseEntity;
import com.korelyakov.restaurant_voting.model.User;
import com.korelyakov.restaurant_voting.util.exception.NotFoundException;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    //  http://stackoverflow.com/a/32728226/548473
    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    // if user didn't vote today -> true
    // if user already voted until 11-00 (exclude) -> true
    // if already voted after 11-00 (include) -> false
    public boolean canVote(User user) {
        return true;
    }
}