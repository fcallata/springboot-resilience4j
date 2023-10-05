package com.test.client.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@RestController
@RequestMapping("/bulkhead")
public class BulkHeadController {

    private static final Logger logger = LoggerFactory.getLogger(BulkHeadController.class);

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Bulkhead implementacion
     * @return
     */
    @GetMapping
    @Bulkhead(name = "orderService", fallbackMethod = "bulkHeadFallback")
    public ResponseEntity<String> createOrder() {
        String response = restTemplate.getForObject("http://localhost:8080/item/orden", String.class);

        logger.info(LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName());

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadFallback(Exception t){
        return new ResponseEntity<String>(" bulkHeadFallback is full and does not permit further calls", HttpStatus.TOO_MANY_REQUESTS);
    }
}
