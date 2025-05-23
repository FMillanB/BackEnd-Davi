package com.squarix.backend_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("testcontainers")
public class TestBackendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.from(BackendProjectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
