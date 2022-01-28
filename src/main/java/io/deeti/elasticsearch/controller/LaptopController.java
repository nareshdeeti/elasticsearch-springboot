package io.deeti.elasticsearch.controller;

import io.deeti.elasticsearch.dto.LaptopDto;
import io.deeti.elasticsearch.esearch.Searcher;
import io.deeti.elasticsearch.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private Searcher searcher;

    @GetMapping(value = "/search")
    public List<LaptopDto> searchLaptop(@RequestParam String search) throws IOException {

//        return search != null ? laptopService.fetchLaptopByModel(search) : new LaptopDto();

        return searcher.elasticSearch(search);
    }
}
