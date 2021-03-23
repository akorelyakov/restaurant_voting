package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Menu;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepository {
    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId()) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    public Menu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    public List<Menu> getAllByRestaurant(int restaurantId) {
        return crudMenuRepository.getAllByRestaurant(restaurantId);
    }

    public List<Menu> getAllByRestaurantAndDate(int restaurantId, LocalDate date) {
        return crudMenuRepository.getAllByRestaurantAndDate(restaurantId, date);
    }
}
