//package dam.Fullstack.ConflictTrackerAPI.mapper;
//
//import dam.Fullstack.ConflictTrackerAPI.dto.conflict.ConflictCreateDto;
//import dam.Fullstack.ConflictTrackerAPI.dto.conflict.ConflictResponseDto;
//import dam.Fullstack.ConflictTrackerAPI.dto.conflict.ConflictUpdateDto;
//import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface ConflictMapper {
//
//    @Mapping(target = "countryCodes", ignore = true)
//    ConflictResponseDto toResponseDto(Conflict conflict);
//
//    List<ConflictResponseDto> toResponseDtos(List<Conflict> conflicts);
//
//    @Mapping(target = "countries", ignore = true)
//    Conflict toEntity(ConflictCreateDto dto);
//
//    @Mapping(target = "countries", ignore = true)
//    Conflict toEntity(ConflictUpdateDto dto);
//}
