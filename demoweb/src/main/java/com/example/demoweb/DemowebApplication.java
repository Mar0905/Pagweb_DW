package com.example.demoweb;

import com.example.demoweb.model.Testimonio;
import com.example.demoweb.repository.TestimonioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemowebApplication.class, args);
	}
	@Bean
    CommandLineRunner run(TestimonioRepository repo) {
        return args -> {
            System.out.println("\nCONEXIÓN EXITOSA A LA BASE DE DATOS");
            System.out.println("TOTAL DE TESTIMONIOS: " + repo.count());

            repo.findAll().forEach(t -> {
                System.out.println(t);
            });

            System.out.println("=====================================\n");
        };
    }

}