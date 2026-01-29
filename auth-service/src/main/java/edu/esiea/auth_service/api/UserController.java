package edu.esiea.auth_service.api;

import edu.esiea.auth_service.app.UserService;
import edu.esiea.auth_service.dto.user.UserDto;
import edu.esiea.auth_service.dto.user.UserPostDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserPostDto userDto)
    {
        return userService.createUser(userDto);
    }
}
