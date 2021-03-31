package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    List<Vote> getByUserId(int userId);

    List<Vote> getByRestaurantId(int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voted=:date")
    Vote getByUserAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
