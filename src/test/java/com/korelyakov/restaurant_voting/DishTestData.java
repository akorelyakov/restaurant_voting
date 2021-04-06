package com.korelyakov.restaurant_voting;


import com.korelyakov.restaurant_voting.model.Dish;
import com.korelyakov.restaurant_voting.model.Restaurant;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static com.korelyakov.restaurant_voting.RestaurantTestData.restaurant1;
import static com.korelyakov.restaurant_voting.RestaurantTestData.restaurant2;
import static com.korelyakov.restaurant_voting.model.AbstractBaseEntity.START_SEQ;


public class DishTestData {
    public static final TestMatcher<Dish> DISH_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Dish.class, "restaurant", "added");

    public static final int DISH_1_ID = START_SEQ + 5;
    public static final int DISH_2_ID = START_SEQ + 6;
    public static final int DISH_3_ID = START_SEQ + 7;
    public static final int DISH_4_ID = START_SEQ + 8;
    public static final int DISH_5_ID = START_SEQ + 9;
    public static final Dish dish1 = new Dish(DISH_1_ID, "Стейк", 1000,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Dish dish2 = new Dish(DISH_2_ID, "Мимоза", 300,
            LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    public static final Dish dish3 = new Dish(DISH_3_ID, "Оливье", 400,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Dish dish4 = new Dish(DISH_4_ID, "Яичница", 180,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Dish dish5 = new Dish(DISH_5_ID, "Тост", 150,
            LocalDate.of(2021, Month.MARCH, 1), restaurant2);
    public static final Set<Dish> dishes1 = Set.of(dish1, dish2);
    public static final Set<Dish> dishes2 = Set.of(dish3, dish4, dish5);

    public static Dish getNew() {
        return new Dish(null, "NewRest", 999, LocalDate.of(2021, Month.MARCH, 1), restaurant1);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish1);
        updated.setName("UpdatedName");
        updated.setPrice(1111);
        return updated;
    }
}
