package com.example.javaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @GetMapping("/health")
    public ResponseEntity<HealthComponent> healthCheck() {
        HealthComponent health = healthEndpoint.health();
        return ResponseEntity.status(WebEndpointResponse.STATUS_OK).body(health);
    }
}
