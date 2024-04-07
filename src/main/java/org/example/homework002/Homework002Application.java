package org.example.homework002;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Online Learning Platform", description = "Education is the spark that ignites curiosity and fuels lifelong learning, transcending the mere accumulation of knowledge.", version = "v1"))
public class Homework002Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework002Application.class, args);
    }

}
