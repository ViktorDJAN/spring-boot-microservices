package ru.kashtanov.moviecatalogservice.model;

public class MovieInfo {
    private String movieId;
    private String name;

    public MovieInfo() {
    }

    public MovieInfo(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "movieId='" + movieId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
