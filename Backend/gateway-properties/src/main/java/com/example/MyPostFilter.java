package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyPostFilter implements GlobalFilter{

	
	Logger log=LoggerFactory.getLogger(MyPostFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	log.debug("============= POST FILTER STARTED ===========");
	ServerHttpResponse res=exchange.getResponse();
	log.debug("headers=====>"+res.getHeaders().toString());
	log.debug("Id     =====>"+res.getStatusCode());
	return chain.filter(exchange)
				.then(Mono.fromRunnable(()->{System.out.println("Global POST Filter Ended...");}));
	}

}
