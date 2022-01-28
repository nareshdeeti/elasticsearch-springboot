package io.deeti.elasticsearch.repository;

import io.deeti.elasticsearch.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    Laptop findLaptopByLaptopModel(String laptopModel);
}