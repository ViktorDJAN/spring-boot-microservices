package ru.kashtanov.moviecatalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kashtanov.moviecatalogservice.model.CatalogItem;
import ru.kashtanov.moviecatalogservice.model.MovieInfo;
import ru.kashtanov.moviecatalogservice.model.Rating;
import ru.kashtanov.moviecatalogservice.model.UserRating;
import ru.kashtanov.moviecatalogservice.repository.CatalogRepository;


import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final CatalogRepository catalogRepository;

    public MovieCatalogController(RestTemplate restTemplate, WebClient.Builder webClientBuilder, CatalogRepository catalogRepository) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.catalogRepository = catalogRepository;
    }

    @GetMapping("/get/{catalogName}")
    public CatalogItem getCatalogItemByName(@PathVariable("catalogName") String catalogName) {
        CatalogRepository catalogRepository1 = new CatalogRepository();
        CatalogItem catalogItem1 = new CatalogItem("Bob", "singer", 100);
        CatalogItem catalogItem2 = new CatalogItem("Super-Man", "hero", 10);
        catalogRepository1.getCatalogRepository().add(catalogItem2);
        catalogRepository1.getCatalogRepository().add(catalogItem1);

        CatalogItem foundItem = new CatalogItem();
        for (CatalogItem item : catalogRepository1.getCatalogRepository()) {
            if (item.getName().equals(catalogName)) {
                foundItem = item;
                System.out.println(foundItem.hashCode() == item.hashCode());
            }
        }


        Rating bearedRating = webClientBuilder.build()
                .get()
                .uri("http://localhost:8088/rating/rated_movies/" + catalogName)
                .retrieve()
                .bodyToMono(Rating.class)
                .block();
        foundItem.setRating(bearedRating.getRating());

        return foundItem;
    }


//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalogByUserId(String userId) {
//
//        //get all rated movie IDs
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3));
//
//        UserRating userRatingList = restTemplate.getForObject("http://localhost:8087/movie-info/" + userId,
//                UserRating.class);
//
//        return ratings.stream().map(rating -> {
//                    //  MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8087/movie-info/" + rating.getMovieId(), MovieInfo.class);
//
//                    MovieInfo movieInfo = webClientBuilder.build()
//                            .get()
//                            .uri("http://localhost:8087/movie-info/" + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(MovieInfo.class)
//                            .block();
//
//                    System.out.println(movieInfo);
//                    return new CatalogItem(movieInfo.getName(), "one", rating.getRating());
//                })
//                .collect(Collectors.toList());
//
//        /// For each movie ID, call movie info service and get its details
//    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogByUserId(String userId) {

        //get all rated movie IDs
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3));
        // here we go to rating-service in rating-controller and take from there the UserRating list.
        UserRating userRatingList = restTemplate.getForObject("http://localhost:8088/rating/users/" + userId,
                UserRating.class);

        System.out.println(userRatingList);

        assert userRatingList != null;
        return userRatingList.getUserRating().stream().map(rating -> {
                    /// For each movie ID, call movie info service and get its details
                    MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8087/movie-info/" + rating.getMovieId(), MovieInfo.class);

                    // Put them all together
                    System.out.println(movieInfo);
                    return new CatalogItem(movieInfo.getName(), "one", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
