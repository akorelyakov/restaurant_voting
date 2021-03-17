//package com.korelyakov.restaurant_voting.web.restaurant;
//
//import com.korelyakov.restaurant_voting.model.Restaurant;
//import com.korelyakov.restaurant_voting.model.Vote;
//import com.korelyakov.restaurant_voting.repository.RestaurantRepository;
//import com.korelyakov.restaurant_voting.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class RestaurantRestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//
//    private RestaurantRepository repository;
//
//    private UserRepository userRepository;
//
//    public List<Restaurant> getAllWithMenu() {
//        log.info("getAllWithMenu");
//        return repository.getAllWithMenu();
//    }
//
//    // сохраняем голос по id ресторана и пользователя
//    public Vote vote(int userId, int restaurantId) {
//        return new Vote(userRepository.get(userId), repository.get(userId, restaurantId));
//    }
//}
