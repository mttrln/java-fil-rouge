package edu.esiea.catalog_service.app;

import edu.esiea.catalog_service.domain.repository.UserRepository;
import edu.esiea.catalog_service.dto.mappers.UserMapper;
import edu.esiea.catalog_service.dto.user.UserDto;
import edu.esiea.catalog_service.dto.user.UserPostDto;
import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(@Valid UserPostDto userDto) {
        var user = UserMapper.INSTANCE.dtoToUserEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userEntityToDTO(userRepository.save(user));
    }
}
