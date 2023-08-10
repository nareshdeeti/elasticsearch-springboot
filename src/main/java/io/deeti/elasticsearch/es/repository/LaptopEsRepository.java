package io.deeti.elasticsearch.es.repository;

import io.deeti.elasticsearch.es.model.Laptop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopEsRepository extends ElasticsearchRepository<Laptop, String> {

    Laptop findByLaptopId(String laptopId);

}
