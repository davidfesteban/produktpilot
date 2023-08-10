package es.misei.everi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EveriApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveriApplication.class, args);
    }

}
