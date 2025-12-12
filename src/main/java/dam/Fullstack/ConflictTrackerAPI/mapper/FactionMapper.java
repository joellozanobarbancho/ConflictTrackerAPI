package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.dto.FactionDto;
import dam.Fullstack.ConflictTrackerAPI.model.Faction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FactionMapper {
    FactionDto toFactionDto(Faction faction);
    List<FactionDto> toFactionDtos(List<Faction> factions);
    Faction toEntity(FactionDto factionDto);
}
