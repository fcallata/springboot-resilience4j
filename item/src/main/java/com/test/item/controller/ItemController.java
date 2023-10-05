package com.test.item.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);
    @GetMapping("/dato")
    public String getDato(@RequestParam String param) throws InterruptedException {
        logger.info("data: {}" , param);

        if (param.equals("1")){
            throw new IllegalArgumentException("ah ocurrdido un error");
        }

        if(param.equals("2")){
            Thread.sleep(5000L);
        }
        return "Data from server";
    }

    @GetMapping("/orden")
    public String getOrden() throws InterruptedException {
        Thread.sleep(5000L);
        return "Data from server";
    }

    @GetMapping("/rate")
    public String getRate() throws InterruptedException {
        return "Data from server";
    }
}


