package ru.kashtanov.movieinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kashtanov.movieinfoservice.model.MovieInfo;

@RestController
@RequestMapping("/movie-info")
public class MovieInfoController {

    @RequestMapping("/{movieId}")
    public MovieInfo getMovieInfo(@PathVariable String movieId){
         return new MovieInfo("Hercules","Myth");
    }
}
