package com.test.client.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ItemService {

    private static Logger logger = LoggerFactory.getLogger(ItemService.class);

    private RestTemplate restTemplate = new RestTemplate();


    @Deprecated
    public void sendData(){
        List<String> data = Arrays.asList("java", "sql", "js", "kotlin", "android", "react", "angular", "hibernate", "kubernetes", "docker");

        Executor executor = Executors.newFixedThreadPool(data.size());

        data.forEach(i -> executor.execute(()-> callExternalApi(i)));
    }

    @CircuitBreaker(name = "api_datos", fallbackMethod = "fallback")
    public String callExternalApi(String param) {
        logger.info("method name callExternalApi {}", param);

        String apiUrl = "http://localhost:8080/item/dato?param={q}";

        return restTemplate.getForObject(apiUrl, String.class, param);
    }

    public String fallback(Exception e) {
        return "Metodo Alternativo";
    }
}
