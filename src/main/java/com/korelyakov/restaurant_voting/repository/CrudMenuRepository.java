package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

}
