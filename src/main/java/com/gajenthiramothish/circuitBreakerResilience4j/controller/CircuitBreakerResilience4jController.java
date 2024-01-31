package com.gajenthiramothish.circuitBreakerResilience4j.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CircuitBreakerResilience4jController {
    private static final String RESILIENCE4J_INSTANCE_NAME = "Resilience4j - circuit-breaker";
    private static final String FALLBACK_METHOD = "fallback";

    @GetMapping(value = "/delay/{delay}")
    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Boolean> delay(@PathVariable int delay) throws InterruptedException {
        Thread.sleep(delay * 1000L);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
    }

    public static ResponseEntity<Boolean> fallback() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE);
    }
}
