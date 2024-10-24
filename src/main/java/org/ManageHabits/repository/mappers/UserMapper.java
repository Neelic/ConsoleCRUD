package org.ManageHabits.repository.mappers;

import org.ManageHabits.repository.dto.UserDTO;
import org.ManageHabits.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
