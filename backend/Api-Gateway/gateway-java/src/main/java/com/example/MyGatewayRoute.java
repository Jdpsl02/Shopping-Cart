package com.example;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyGatewayRoute {

	
	@Bean
	public RouteLocator getwayRounte(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/appone/**")
						.uri("http://localhost:8001"))
				.route(r->r.path("/product/**")
						.uri("http://localhost:8002"))
				.route(r->r.path("/appthree/**")
						.uri("http://localhost:8003"))
				.build();
				
	}
}
