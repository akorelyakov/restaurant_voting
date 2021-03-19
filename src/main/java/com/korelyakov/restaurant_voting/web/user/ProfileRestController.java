package com.korelyakov.restaurant_voting.web.user;

import com.korelyakov.restaurant_voting.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.korelyakov.restaurant_voting.web.SecurityUtil.authUserId;

//@RestController
//@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class ProfileRestController extends AbstractUserController {
//    static final String REST_URL = "/rest/profile";

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