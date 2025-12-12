package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.dto.EventDto;
import dam.Fullstack.ConflictTrackerAPI.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toEventDto(Event event);
    List<EventDto> toEventDtos(List<Event> events);
    Event toEntity(EventDto eventDto);
}
