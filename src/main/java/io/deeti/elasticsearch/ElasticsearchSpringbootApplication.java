package io.deeti.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "io.deeti.elasticsearch.es.repository")
@SpringBootApplication
public class ElasticsearchSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchSpringbootApplication.class, args);
	}

}
