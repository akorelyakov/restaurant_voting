package com.korelyakov.restaurant_voting.web.vote;

import com.korelyakov.restaurant_voting.model.Vote;
import com.korelyakov.restaurant_voting.repository.VoteRepository;
import com.korelyakov.restaurant_voting.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.korelyakov.restaurant_voting.util.ValidationUtil.*;

public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository repository;

    public VoteRestController(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Vote create(Vote vote) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return repository.save(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return repository.getAll(userId);
    }
}