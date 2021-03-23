package com.korelyakov.restaurant_voting;


import com.korelyakov.restaurant_voting.model.Menu;
import com.korelyakov.restaurant_voting.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.korelyakov.restaurant_voting.DishTestData.dishes1;
import static com.korelyakov.restaurant_voting.DishTestData.dishes2;
import static com.korelyakov.restaurant_voting.RestaurantTestData.*;


public class MenuTestData {
    public static final TestMatcher<Restaurant> MENU_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final Menu menuRest1 = new Menu(MENU_1_ID, restaurant1, LocalDate.of(2021, Month.MARCH, 1), dishes1);
    public static final Menu menuRest2 = new Menu(MENU_2_ID, restaurant2, LocalDate.of(2021, Month.MARCH, 1),
            dishes2);
    public static final List<Menu> menuListRest1 = Arrays.asList(menuRest1);
    public static final List<Menu> menuListRest2 = Arrays.asList(menuRest2);
}
