package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.event.*;
import dam.Fullstack.ConflictTrackerAPI.mapper.EventMapper;
import dam.Fullstack.ConflictTrackerAPI.model.Event;
import dam.Fullstack.ConflictTrackerAPI.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService service;
    private final EventMapper mapper;

    public EventController(EventService service, EventMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<EventResponseDto> list() {
        return service.findAll().stream()
                .map(event -> new EventResponseDto(
                        event.getId(),
                        event.getEventDate(),
                        event.getLocation(),
                        event.getDescription(),
                        event.getConflict().getId()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public EventResponseDto get(@PathVariable Long id) {
        Event event = service.findById(id);
        return new EventResponseDto(
                event.getId(),
                event.getEventDate(),
                event.getLocation(),
                event.getDescription(),
                event.getConflict().getId()
        );
    }

    @PostMapping
    public EventResponseDto create(@RequestBody EventCreateDto dto) {
        Event event = mapper.toEntity(dto);
        Event saved = service.create(event, dto.conflictId());
        return new EventResponseDto(
                saved.getId(),
                saved.getEventDate(),
                saved.getLocation(),
                saved.getDescription(),
                saved.getConflict().getId()
        );
    }

    @PutMapping("/{id}")
    public EventResponseDto update(@PathVariable Long id, @RequestBody EventUpdateDto dto) {
        Event event = mapper.toEntity(dto);
        Event updated = service.update(id, event, dto.conflictId());
        return new EventResponseDto(
                updated.getId(),
                updated.getEventDate(),
                updated.getLocation(),
                updated.getDescription(),
                updated.getConflict().getId()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
