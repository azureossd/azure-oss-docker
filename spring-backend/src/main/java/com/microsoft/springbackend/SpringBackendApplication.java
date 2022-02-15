package com.microsoft.springbackend;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@org.springframework.boot.autoconfigure.SpringBootApplication
@RestController
public class SpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	@Profile("!docker")
	public Server h2Server() {
		Server h2Server;
		try {
			h2Server = Server.createTcpServer();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to start H2 server: ", e);
		}
		return h2Server;
	}
}
