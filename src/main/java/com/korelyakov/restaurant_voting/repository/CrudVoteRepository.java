package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
}
