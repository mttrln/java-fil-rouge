package edu.esiea.gateway_service.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HealthController {

    @GetMapping("/health")
    public String checkHealth() {
        return "Alive and well!";
    }
}
