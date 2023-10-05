package com.test.client.controller;

import com.test.client.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/circuit")
public class CircuitBreakerController {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @Autowired
    ItemService itemService;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * CircuitBraker implementacion
     * @param param
     * @return
     */
    @GetMapping("/{param}")
    public String init(@PathVariable String param){
        return itemService.callExternalApi(param);
    }

}
