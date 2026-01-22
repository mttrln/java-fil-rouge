package edu.esiea.tp_sb.api;

import edu.esiea.tp_sb.app.AuthService;
import edu.esiea.tp_sb.app.ThemeService;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;
import edu.esiea.tp_sb.dto.lesson.LessonPostDto;
import edu.esiea.tp_sb.dto.theme.ThemeDto;
import edu.esiea.tp_sb.dto.theme.ThemePostDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        return authService.authenticate(username, password);
    }
}
