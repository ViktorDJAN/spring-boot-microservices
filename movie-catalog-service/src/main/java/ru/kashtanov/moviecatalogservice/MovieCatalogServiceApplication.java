package ru.kashtanov.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kashtanov.moviecatalogservice.model.CatalogItem;
import ru.kashtanov.moviecatalogservice.repository.CatalogRepository;

@SpringBootApplication
public class MovieCatalogServiceApplication {
    @Bean
    public RestTemplate getRestTemplate() {
         return new RestTemplate();
    }
    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
