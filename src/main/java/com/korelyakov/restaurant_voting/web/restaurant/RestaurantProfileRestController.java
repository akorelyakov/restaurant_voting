package com.korelyakov.restaurant_voting.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController {
    static final String REST_URL = "/rest/profile/restaurant";

}
