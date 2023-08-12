package io.deeti.elasticsearch.controller;

import io.deeti.elasticsearch.dto.LaptopDto;
import io.deeti.elasticsearch.es.model.Laptop;
import io.deeti.elasticsearch.es.service.Searcher;
import io.deeti.elasticsearch.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private Searcher searcher;

    @GetMapping(value = "/search")
    public List<Laptop> searchLaptop(@RequestParam String search) throws IOException {

//        return search != null ? laptopService.fetchLaptopByModel(search) : new LaptopDto();

        return searcher.elasticSearch(search);
    }

    @PostMapping("/save")
    void save(@RequestBody Laptop laptop) {
        searcher.saveLaptop(laptop);
    }

}
