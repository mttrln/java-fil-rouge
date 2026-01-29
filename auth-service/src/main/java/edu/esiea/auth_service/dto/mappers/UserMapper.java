package edu.esiea.auth_service.dto.mappers;

import edu.esiea.auth_service.domain.entity.UserEntity;
import edu.esiea.auth_service.dto.user.UserDto;
import edu.esiea.auth_service.dto.user.UserPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userEntityToDTO(UserEntity user);
    UserEntity dtoToUserEntity(UserPostDto user);
}
