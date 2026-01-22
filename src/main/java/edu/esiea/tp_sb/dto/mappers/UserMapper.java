package edu.esiea.tp_sb.dto.mappers;

import edu.esiea.tp_sb.domain.entity.UserEntity;
import edu.esiea.tp_sb.dto.user.UserDto;
import edu.esiea.tp_sb.dto.user.UserPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userEntityToDTO(UserEntity user);
    UserEntity dtoToUserEntity(UserPostDto user);
}
