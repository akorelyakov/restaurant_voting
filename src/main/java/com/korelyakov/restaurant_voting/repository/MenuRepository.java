package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Menu;
import com.korelyakov.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional
    @Modifying
    Menu save(Menu menu, int menu_id);

    @Transactional
    @Modifying
    boolean delete(int menu_id);

    Menu get(int menu_id);

    List<Menu> getAllByDate(LocalDate date);

    List<Menu> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);

    List<Menu> getAllByRestaurant(int restaurant_id);
}
