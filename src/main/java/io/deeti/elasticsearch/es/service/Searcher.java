package io.deeti.elasticsearch.es.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeti.elasticsearch.es.model.Laptop;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Searcher helps to get the search results
 */
@Component
public class Searcher {

    private final ElasticsearchOperations client;

    public Searcher(ElasticsearchOperations client) {
        this.client = client;
    }

    /**
     * @param text given text
     * @return returns search results
     */
    public List<Laptop> elasticSearch(String text) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TermsAggregationBuilder countByBrand = new TermsAggregationBuilder("countByBrand")
                .field("brand.keyword");

        FieldSortBuilder laptopModel = SortBuilders.fieldSort("laptopModel.keyword");

        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(text, "laptopModel", "brand"))
                .withAggregations(countByBrand)
                .withSorts(laptopModel)
                .build();

        SearchHits<Laptop> searchHits = client.search(query, Laptop.class);
        AggregationsContainer<?> aggregations = searchHits.getAggregations();

        if (aggregations != null) {
            Object aggregation = aggregations.aggregations();
        }

        return searchHits
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    public void saveLaptop(Laptop laptop) {
        client.save(laptop);
    }

}
