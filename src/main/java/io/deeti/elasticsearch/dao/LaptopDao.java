package io.deeti.elasticsearch.dao;

import io.deeti.elasticsearch.dto.LaptopDto;
import io.deeti.elasticsearch.entity.Laptop;
import io.deeti.elasticsearch.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopDao {

    @Autowired
    private LaptopRepository laptopRepository;

    public Laptop getLaptopByModel(String model) {

        return laptopRepository.findLaptopByLaptopModel(model);
    }
}
