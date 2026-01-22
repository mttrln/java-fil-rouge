package edu.esiea.tp_sb.api;

import edu.esiea.tp_sb.app.AuthService;
import edu.esiea.tp_sb.dto.user.LoginDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto user) throws AuthenticationException {
        return authService.authenticate(user.username, user.password);
    }
}
