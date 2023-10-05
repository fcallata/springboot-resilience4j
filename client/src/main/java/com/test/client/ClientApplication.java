package com.test.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@SpringBootApplication
public class ClientApplication {
	private static final Logger logger = LoggerFactory.getLogger(ClientApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
		int i=1;
		IntStream.range(i,10).parallel().forEach(t->{
			String response = new RestTemplate().getForObject("http://localhost:8090/bulkhead", String.class);
			logger.info("response: {}", response);

		});
	}

}
