package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.event.*;
import dam.Fullstack.ConflictTrackerAPI.model.Event;
import dam.Fullstack.ConflictTrackerAPI.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(
            @RequestParam(required = false) Long conflictId) {
        List<EventDTO> events;
        if (conflictId != null) {
            events = eventService.getEventsByConflictId(conflictId);
        } else {
            events = eventService.getAllEvents();
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        EventDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventCreateDTO createDTO) {
        EventDTO created = eventService.createEvent(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody EventCreateDTO updateDTO) {
        EventDTO updated = eventService.updateEvent(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/range")
    public ResponseEntity<List<EventDTO>> getEventsByDateRange(
            @RequestParam String start,
            @RequestParam String end) {
        return ResponseEntity.ok(
                eventService.getEventsByDateRange(start, end)
        );
    }

    @GetMapping("/location/{text}")
    public ResponseEntity<List<EventDTO>> getEventsByLocation(
            @PathVariable String text) {
        return ResponseEntity.ok(eventService.getEventsByLocation(text));
    }

}