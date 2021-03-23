package com.korelyakov.restaurant_voting;


import com.korelyakov.restaurant_voting.model.Dish;
import com.korelyakov.restaurant_voting.model.Restaurant;

import java.util.Set;

import static com.korelyakov.restaurant_voting.MenuTestData.menuRest1;
import static com.korelyakov.restaurant_voting.MenuTestData.menuRest2;
import static com.korelyakov.restaurant_voting.RestaurantTestData.*;


public class DishTestData {
    public static final TestMatcher<Restaurant> DISH_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final Dish dish1 = new Dish(DISH_1_ID, "Стейк", 1000, menuRest1);
    public static final Dish dish2 = new Dish(DISH_2_ID, "Мимоза", 300, menuRest1);
    public static final Dish dish3 = new Dish(DISH_3_ID, "Оливье", 400, menuRest2);
    public static final Dish dish4 = new Dish(DISH_4_ID, "Яичница", 180, menuRest2);
    public static final Dish dish5 = new Dish(DISH_5_ID, "Тост", 150, menuRest2);

    public static final Set<Dish> dishes1 = Set.of(dish1, dish2);
    public static final Set<Dish> dishes2 = Set.of(dish3, dish4, dish5);
}
