package com.squarix.backend_project;

import com.squarix.backend_project.model.Usuario;
import com.squarix.backend_project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository repo) {
		return args -> {
			repo.save(new Usuario("Freddy Millan", 21L, "ESTUDIANTE", "a@a.com", "1234"));
			repo.save(new Usuario("Santiago Villa", 22L, "PROFESOR", "e@e.com", "1234"));
			repo.save(new Usuario("Andres Becerra", 22L, "PROFESOR", "i@i.com", "1234"));
			repo.save(new Usuario("Andres Perez", 22L, "PROFESOR", "andres@a.com", "1234"));
			repo.save(new Usuario("Johan Cabrera", 22L, "ADMIN", "admin@a.com", "1234"));
		};
	}
}
