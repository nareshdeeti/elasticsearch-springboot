package io.deeti.elasticsearch.service;

import io.deeti.elasticsearch.dao.LaptopDao;
import io.deeti.elasticsearch.dto.LaptopDto;
import io.deeti.elasticsearch.entity.Laptop;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopDao laptopDao;

    public LaptopDto fetchLaptopByModel(String model) {

        LaptopDto laptopDto = new LaptopDto(model);

        Laptop laptopByModel = laptopDao.getLaptopByModel(laptopDto.getLaptopModel());

        BeanUtils.copyProperties(laptopByModel, laptopDto);

        return laptopDto;
    }
}
