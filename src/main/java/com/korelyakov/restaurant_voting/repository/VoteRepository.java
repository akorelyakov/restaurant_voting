package com.korelyakov.restaurant_voting.repository;

import com.korelyakov.restaurant_voting.model.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VoteRepository {
    private final CrudVoteRepository crudVoteRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    private final CrudUserRepository crudUserRepository;

    public VoteRepository(CrudVoteRepository crudVoteRepository, CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId()) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    public List<Vote> getAllByUser(int userId) {
        return crudVoteRepository.getByUser(userId);
    }

    public List<Vote> getAllByRestaurant(int restaurantId) {
        return crudVoteRepository.getByRestaurant(restaurantId);
    }
}
