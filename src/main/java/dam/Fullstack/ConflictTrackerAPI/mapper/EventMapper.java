package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.dto.event.EventCreateDto;
import dam.Fullstack.ConflictTrackerAPI.dto.event.EventResponseDto;
import dam.Fullstack.ConflictTrackerAPI.dto.event.EventUpdateDto;
import dam.Fullstack.ConflictTrackerAPI.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "conflictId", ignore = true)
    EventResponseDto toResponseDto(Event event);

    List<EventResponseDto> toResponseDtos(List<Event> events);

    @Mapping(target = "conflict", ignore = true)
    Event toEntity(EventCreateDto dto);

    @Mapping(target = "conflict", ignore = true)
    Event toEntity(EventUpdateDto dto);
}
