package br.com.felipestoco.buildersmvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BuildersMvpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildersMvpApplication.class, args);
    }

}
