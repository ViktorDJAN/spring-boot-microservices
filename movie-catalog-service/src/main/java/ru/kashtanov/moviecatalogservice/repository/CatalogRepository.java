package ru.kashtanov.moviecatalogservice.repository;

import org.springframework.stereotype.Component;
import ru.kashtanov.moviecatalogservice.model.CatalogItem;

import java.util.ArrayList;
import java.util.List;
@Component
public class CatalogRepository {
    private  List<CatalogItem>  catalogRepository = new ArrayList<>();

    public List<CatalogItem> getCatalogRepository() {
        return catalogRepository;
    }

    public void setCatalogRepository(List<CatalogItem> catalogRepository) {
        this.catalogRepository = catalogRepository;
    }
}
