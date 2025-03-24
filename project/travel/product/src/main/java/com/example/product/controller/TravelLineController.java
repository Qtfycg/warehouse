package com.example.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/line")
public class TravelLineController {

    @GetMapping("/list")
    public Map<String, Object> list() {
        return Map.of("data", List.of("西安兵马俑一日游", "云南丽江五日游"));
    }
}
