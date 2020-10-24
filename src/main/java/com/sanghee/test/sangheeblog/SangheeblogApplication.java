package com.sanghee.test.sangheeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SangheeblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SangheeblogApplication.class, args);
    }

}
