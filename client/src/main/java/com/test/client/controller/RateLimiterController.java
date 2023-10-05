package com.test.client.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
@RequestMapping("/ratelimiter")
public class RateLimiterController {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterController.class);

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Rate limiter implementacion
     * @return
     */
    @GetMapping
    @RateLimiter(name = "simpleRateLimit", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<String> createRate() {
        String response = restTemplate.getForObject("http://localhost:8080/item/rate", String.class);

        logger.info(LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName());

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> rateLimiterFallback(Exception t){
        return new ResponseEntity<String>(" rateLimiterFallback is full and does not permit further calls", HttpStatus.TOO_MANY_REQUESTS);
    }
}
