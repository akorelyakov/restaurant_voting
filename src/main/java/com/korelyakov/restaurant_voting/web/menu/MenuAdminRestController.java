package com.korelyakov.restaurant_voting.web.menu;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = MenuAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuAdminRestController {
    static final String REST_URL = "/rest/admin/restaurant/menu";

    // add dish to menu

    // get menu

    // delete menu

    // update menu

    // get all by rest_id


}
