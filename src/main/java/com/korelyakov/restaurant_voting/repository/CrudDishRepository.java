package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant_id")
    List<Dish> getAllByRestaurant(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant_id AND d.added=:date")
    List<Dish> getAllByRestaurantAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);
}
