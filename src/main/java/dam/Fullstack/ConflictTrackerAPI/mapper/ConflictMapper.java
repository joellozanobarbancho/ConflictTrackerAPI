package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.dto.ConflictDto;
import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConflictMapper {
    ConflictDto toConflictDto(Conflict conflict);
    List<ConflictDto> toConflictDtos(List<Conflict> conflicts);
    Conflict toEntity(ConflictDto conflictDto);
}
