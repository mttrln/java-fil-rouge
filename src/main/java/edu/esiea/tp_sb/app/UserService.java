package edu.esiea.tp_sb.app;

import edu.esiea.tp_sb.domain.repository.UserRepository;
import edu.esiea.tp_sb.dto.mappers.UserMapper;
import edu.esiea.tp_sb.dto.user.UserDto;
import edu.esiea.tp_sb.dto.user.UserPostDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(@Valid UserPostDto userDto) {
        return UserMapper.INSTANCE.userEntityToDTO(userRepository.save(UserMapper.INSTANCE.dtoToUserEntity(userDto)));
    }
}
