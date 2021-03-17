package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    Restaurant save(Restaurant restaurant, int restaurant_id);

    @Transactional
    @Modifying
    boolean delete(int restaurant_id);

    Restaurant get(int restaurant_id);

    Restaurant getWithMenu(int restaurant_id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithMenuByDate(LocalDate date);
}
