package com.example;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "*", maxAge = 3600)
public class MyGatewayRoute {

	
	@Bean
	public RouteLocator getwayRounte(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/api/**")
						.uri("http://localhost:9098"))
				.route(r->r.path("/profile/**")
						.uri("http://localhost:8081"))
				.route(r->r.path("/product/**")
						.uri("http://localhost:8082"))
				.route(r->r.path("/cart/**")
						.uri("http://localhost:8083"))
				.route(r->r.path("/order/**")
						.uri("http://localhost:8084"))
				.route(r->r.path("/ewallet/**")
						.uri("http://localhost:8085"))
				.build();
				
	}
}
