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

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus")
    List<Restaurant> getAllWithMenus();

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus WHERE r.id =:id")
    Restaurant getWithMenu(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r, Menu m LEFT JOIN FETCH r.menus WHERE m.added=:date")
    List<Restaurant> getAllWithMenusByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r, Menu m LEFT JOIN FETCH r.menus WHERE r.id=:id AND m.added=:date")
    List<Restaurant> getAllWithMenusByDate(int id, LocalDate date);
}
