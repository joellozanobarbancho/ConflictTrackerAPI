//package dam.Fullstack.ConflictTrackerAPI.mapper;
//
//import dam.Fullstack.ConflictTrackerAPI.model.Faction;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface FactionMapper {
//
//    @Mapping(target = "conflictId", ignore = true)
//    @Mapping(target = "supporterCountryCodes", ignore = true)
//    FactionResponseDto toResponseDto(Faction faction);
//
//    List<FactionResponseDto> toResponseDtos(List<Faction> factions);
//
//    @Mapping(target = "conflict", ignore = true)
//    @Mapping(target = "countries", ignore = true)
//    Faction toEntity(FactionCreateDto dto);
//
//    @Mapping(target = "conflict", ignore = true)
//    @Mapping(target = "countries", ignore = true)
//    Faction toEntity(FactionUpdateDto dto);
//}
