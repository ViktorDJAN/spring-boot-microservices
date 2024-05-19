package ru.kashtanov.ratingdataservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kashtanov.ratingdataservice.model.Rating;
import ru.kashtanov.ratingdataservice.model.UserRating;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingDataController {

    @RequestMapping("/{movieId}")
    public Rating getRatingData(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("/rated_movies/{movieId}")
    public Rating getUserData(@PathVariable("movieId") String movieId){
        List<Rating>ratings = Arrays.asList(
                new Rating("Bob",999)
                ,new Rating("Super-Man",255)
        );

        for(Rating rating: ratings){
            if(rating.getMovieId().equals(movieId)){
                return rating;
            }
        }
        return null;
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId){
        List<Rating>ratings = Arrays.asList(
                new Rating("Bob",999)
                ,new Rating("Super-Man",255)
        );

        return new UserRating(ratings);
    }
}
