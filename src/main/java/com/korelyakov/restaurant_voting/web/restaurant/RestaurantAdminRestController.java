package com.korelyakov.restaurant_voting.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    static final String REST_URL = "/rest/admin/restaurant";
    //save restaurant

    //get restaurant

    //update restaurant

    //save menu to restaurant by id

    //update menu to restaurant by id

    //add restaurant
}
