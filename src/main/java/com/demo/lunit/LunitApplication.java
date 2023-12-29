package com.demo.lunit;

import com.demo.lunit.entities.Slide;
import com.demo.lunit.entities.Status;
import com.demo.lunit.entities.User;
import com.demo.lunit.respositories.SlideRepository;
import com.demo.lunit.respositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LunitApplication {
    private static final Logger log = LoggerFactory.getLogger(LunitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LunitApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, SlideRepository slideRepository) {
        return (args) -> {
            // save a few users
            User user1 = new User(1L, "Jack", "jack", "pass1","test1@email.com", "010-692-6593");
            User user2 = new User(2L, "Park", "park", "pass2","test2@email.com", "010-692-6593");
            User user3 = new User(3L, "Kim", "kim", "pass3","test3@email.com", "010-692-6593");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // save a few slides
            Slide slide1 = new Slide(1L,"testSlide1", false, Status.NONE, 0,0.0,"testDesc","svs","bla",234L, "sha234", 2l);
            Slide slide2 = new Slide(2L,"testSlide2", true, Status.COMPLETED, 0,1.0,"testDesc","svs","bla",234L, "sha234", 2l);
            Slide slide3 = new Slide(3L,"testSlide3", false, Status.FAILED, 0,0.0,"testDesc","svs","bla",234L, "sha234", 3l);

            slideRepository.save(slide1);
            slideRepository.save(slide2);
            slideRepository.save(slide3);

            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            userRepository.findAll().forEach(user -> {
                log.info(user.toString());
            });
            log.info("");

            // fetch all slides
            log.info("Slides found with findAll():");
            log.info("-------------------------------");
            slideRepository.findAll().forEach(slide -> {
                log.info(slide.toString());
            });
            log.info("");
        };
    }
}
