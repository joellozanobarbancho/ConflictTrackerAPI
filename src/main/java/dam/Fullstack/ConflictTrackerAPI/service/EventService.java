package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.dto.event.*;
import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.model.Event;
import dam.Fullstack.ConflictTrackerAPI.repository.ConflictRepository;
import dam.Fullstack.ConflictTrackerAPI.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;

    public EventService(EventRepository eventRepository, ConflictRepository conflictRepository) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
        return toDTO(event);
    }

    public List<EventDTO> getEventsByConflictId(Long conflictId) {
        return eventRepository.findByConflictIdOrderByEventDateDesc(conflictId).stream()
                .map(this::toDTO)
                .toList();
    }

    public EventDTO createEvent(EventCreateDTO createDTO) {
        Conflict conflict = conflictRepository.findById(createDTO.conflictId())
                .orElseThrow(() -> new NotFoundException("Conflict not found with id: " + createDTO.conflictId()));

        Event event = new Event();
        event.setEventDate(createDTO.eventDate());
        event.setLocation(createDTO.location());
        event.setDescription(createDTO.description());
        event.setConflict(conflict);

        Event saved = eventRepository.save(event);
        return toDTO(saved);
    }

    public EventDTO updateEvent(Long id, EventCreateDTO updateDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));

        Conflict conflict = conflictRepository.findById(updateDTO.conflictId())
                .orElseThrow(() -> new NotFoundException("Conflict not found with id: " + updateDTO.conflictId()));

        event.setEventDate(updateDTO.eventDate());
        event.setLocation(updateDTO.location());
        event.setDescription(updateDTO.description());
        event.setConflict(conflict);

        Event saved = eventRepository.save(event);
        return toDTO(saved);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    private EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getEventDate(),
                event.getLocation(),
                event.getDescription(),
                event.getConflict().getId(),
                event.getConflict().getName()
        );
    }
}
