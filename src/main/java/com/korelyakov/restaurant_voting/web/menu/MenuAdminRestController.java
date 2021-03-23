package com.korelyakov.restaurant_voting.web.menu;

import com.korelyakov.restaurant_voting.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = MenuAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuAdminRestController {
    static final String REST_URL = "/rest/admin/restaurant/{id}/menu";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    // add dish to menu

    // get menu

    // delete menu

    // update menu

    // get all by rest_id


    public void addMenu(Menu menu, int restaurantId) {
        log.info("save menu {} to restaurant with id={}", menu, restaurantId);
    }

    public void updateMenu(Menu menu, int restaurantId) {
        log.info("update menu {} to restaurant with id={}", menu, restaurantId);
    }

    public void deleteMenu(Menu menu, int restaurantId) {
        log.info("delete menu {} to restaurant with id={}", menu, restaurantId);
    }
}
