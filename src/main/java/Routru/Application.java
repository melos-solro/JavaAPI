package Routru;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by nisargap on 3/21/16.
 * Copyright 2016 Routru
 * Filename: Application.java
 * Package: Routru
 * Description: This is perhaps the most important aspect of the Routru API as it is responsible for making sure the server runs.
 * For a basic guide on creating a REST API with the Spring framework checkout this link: https://spring.io/guides/gs/rest-service/
 **/

// The decorator below states that this Application class is the main boot application for the Spring framework.
@SpringBootApplication
public class Application {


    public static void main(String[] args) {

        // Runs the appliction.
        SpringApplication.run(Application.class, args);

    }

}
