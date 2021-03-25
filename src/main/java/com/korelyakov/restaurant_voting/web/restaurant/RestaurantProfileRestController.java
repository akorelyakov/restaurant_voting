package com.korelyakov.restaurant_voting.web.restaurant;

import com.korelyakov.restaurant_voting.model.Restaurant;
import com.korelyakov.restaurant_voting.model.Vote;
import com.korelyakov.restaurant_voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

//@RestController
//@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class RestaurantProfileRestController extends AbstractRestaurantController {
//    static final String REST_URL = "/rest/profile/restaurant";

    @Autowired
    private VoteRepository voteRepository;

    public List<Restaurant> getAllWithDishesForToday() {
        return super.getAllWithDishesByDate(LocalDate.now());
    }

    public Vote vote(int restaurantId) {
        // get userId?
        // check ValidationUtil.canVote
        // save vote by restId, userId, LocalDate.now()
        return null;
    }


}
