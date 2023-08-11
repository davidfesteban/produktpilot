package dev.misei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableMongoRepositories
public class ProduktPilotApplication {
    //TODO: Maybe we need to add a listener and a lock for each call because of the way of managing organizations
    public static void main(String[] args) {
        SpringApplication.run(ProduktPilotApplication.class, args);
    }

}