package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant=:restaurant_id")
    List<Menu> getAllByRestaurant(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant=:restaurantId AND m.added=:date")
    List<Menu> getAllByRestaurantAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);
}
