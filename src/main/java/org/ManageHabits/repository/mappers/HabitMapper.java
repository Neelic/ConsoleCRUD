package org.ManageHabits.repository.mappers;

import org.ManageHabits.repository.dto.HabitDTO;
import org.ManageHabits.repository.entity.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HabitMapper {
    HabitMapper INSTANCE = Mappers.getMapper(HabitMapper.class);

    HabitDTO toDTO(Habit habit);
    Habit toEntity(HabitDTO habitDTO);
}
