package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    Dish save(Dish dish, int dish_id);

    @Transactional
    @Modifying
    boolean delete(int dish_id);

    Dish get(int dish_id);

    List<Dish> getAll();
}
