package io.deeti.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import static org.springframework.data.elasticsearch.client.RestClients.create;

@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("https://fusnpq63pa.loclx.io/")
                .build();
        return create(clientConfiguration)
                .rest();
    }
}
