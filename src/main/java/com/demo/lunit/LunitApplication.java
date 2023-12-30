package com.demo.lunit;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.entities.User;
import com.demo.lunit.respositories.SlideRepository;
import com.demo.lunit.respositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                description = "Test project for Lunit Assignment",
                title = "Lunit service APIs",
                version = "1.0.0",
                contact = @Contact(name="Bulgan Galbadrakh",
                            email = "bulgan.galbadrakh@gmail.com")),
        servers = @Server(url = "http://localhost:8080",
                description = "Local ENV")
)
public class LunitApplication {
    private static final Logger logger = LoggerFactory.getLogger(LunitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LunitApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, SlideRepository slideRepository) {
        return (args) -> {
            // save a few users
            User user1 = new User(1L, "Jack", "jack", "pass","test1@email.com", "010-692-6593");
            User user2 = new User(2L, "Park", "park", "pass","test2@email.com", "010-692-6593");
            User user3 = new User(3L, "Kim", "kim", "pass","test3@email.com", "010-692-6593");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // save a few slides
            Slide slide1 = new Slide(1L,"CMU-1-Small-Region.svs", false, Status.NONE, null,null,"Exported region from CMU-1.svs","Aperio SVS","CC0-1.0",1938955L, "ed92d5a9f2e86df6", 2l);
            Slide slide2 = new Slide(2L,"CMU-1-Small-Region.svs", true, Status.NONE, null,null,"Exported region from CMU-1.svs","Aperio SVS","CC0-1.0",1938955L, "ed92d5a9f2e86df6", 2l);
            Slide slide3 = new Slide(3L,"CMU-1-Small-Region.svs", false, Status.NONE, null,null,"Exported region from CMU-1.svs","Aperio SVS","CC0-1.0",1938955L, "ed92d5a9f2e86df6", 3l);

            slideRepository.save(slide1);
            slideRepository.save(slide2);
            slideRepository.save(slide3);

            // fetch all users
            logger.info("Users found with findAll():");
            logger.info("-------------------------------");
            userRepository.findAll().forEach(user -> {
                logger.info(user.toString());
            });
            logger.info("");

            // fetch all slides
            logger.info("Slides found with findAll():");
            logger.info("-------------------------------");
            slideRepository.findAll().forEach(slide -> {
                logger.info(slide.toString());
            });
            logger.info("");
        };
    }
}
