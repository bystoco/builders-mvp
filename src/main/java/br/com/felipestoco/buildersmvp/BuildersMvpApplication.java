package br.com.felipestoco.buildersmvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableSpringDataWebSupport
@RestController
public class BuildersMvpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildersMvpApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Teste Builders: Felipe Stoco";
    }

}
