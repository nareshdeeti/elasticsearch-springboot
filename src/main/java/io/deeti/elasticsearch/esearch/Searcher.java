package io.deeti.elasticsearch.esearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.deeti.elasticsearch.dto.LaptopDto;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *Searcher helps to get the search results
 */
@Component
public class Searcher {

    @Autowired
    private RestHighLevelClient client;

    /**
     * @param text
     * @return returns search results
     * @throws IOException
     */
    public List<LaptopDto> elasticSearch(String text) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.simpleQueryStringQuery(text));

        SearchRequest request = new SearchRequest();

        request.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        request.source(searchBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        response.getHits();

        return Stream.of(response.getHits().getHits())
                .map(hit -> mapper.convertValue(hit, LaptopDto.class))
                .collect(Collectors.toList());
    }
}
