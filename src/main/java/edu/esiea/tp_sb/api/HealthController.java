package edu.esiea.tp_sb.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping
    public String checkHealth() {
        return "Alive and well!";
    }
}
