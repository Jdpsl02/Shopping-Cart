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
public class MyPreFilter implements GlobalFilter{

	
	Logger log=LoggerFactory.getLogger(MyPreFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	log.debug("============= PRE FILTER ===========");
	ServerHttpRequest request=exchange.getRequest();
	log.debug("headers=====>"+request.getHeaders().toString());
	log.debug("Id     =====>"+request.getId());
	log.debug("Pathn  =====>"+request.getPath());
	log.debug("Local Adrress=====>"+request.getLocalAddress());
	log.debug("Remote Address=====>"+request.getRemoteAddress());
	log.debug("URI=====>"+request.getURI());
	log.debug("============== PRE FILTER END============");
		return chain.filter(exchange);
	}

}
