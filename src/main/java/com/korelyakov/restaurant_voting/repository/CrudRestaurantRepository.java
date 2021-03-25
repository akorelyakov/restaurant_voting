package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes WHERE r.id =:id")
    Restaurant getWithDishes(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r, Dish d LEFT JOIN FETCH r.dishes WHERE d.added=:date")
    List<Restaurant> getAllWithDishesByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r, Dish d LEFT JOIN FETCH r.dishes WHERE r.id=:id AND d.added=:date")
    List<Restaurant> getWithDishesByDate(@Param("id") int id, @Param("date") LocalDate date);
}
