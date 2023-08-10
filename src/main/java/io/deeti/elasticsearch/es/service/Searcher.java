package io.deeti.elasticsearch.es.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeti.elasticsearch.dto.LaptopDto;
import io.deeti.elasticsearch.es.model.Laptop;
import io.deeti.elasticsearch.es.repository.LaptopEsRepository;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Searcher helps to get the search results
 */
@Component
public class Searcher {

    private final ElasticsearchOperations client;
    private final LaptopEsRepository laptopEsRepository;

    public Searcher(ElasticsearchOperations client,
                    LaptopEsRepository laptopEsRepository) {
        this.client = client;
        this.laptopEsRepository = laptopEsRepository;
    }

    /**
     * @param text
     * @return returns search results
     * @throws IOException
     */
    public List<LaptopDto> elasticSearch(String text) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("laptopModel", text))
                .withQuery(QueryBuilders.matchQuery("brand", text))
                .build();

        SearchHits<Laptop> searchHits = client.search(query, Laptop.class);
        return Stream.of(searchHits)
                .map(hit -> mapper.convertValue(hit, LaptopDto.class))
                .collect(Collectors.toList());
    }

    public Laptop getByLaptopId(String id) {
        return laptopEsRepository.findByLaptopId(id);
    }

}
