package dam.Fullstack.ConflictTrackerAPI.service;

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

    private final EventRepository eventRepo;
    private final ConflictRepository conflictRepo;

    public EventService(EventRepository eventRepo, ConflictRepository conflictRepo) {
        this.eventRepo = eventRepo;
        this.conflictRepo = conflictRepo;
    }

    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    public Event findById(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Event %d not found".formatted(id)));
    }

    public Event create(Event event, Long conflictId) {
        Conflict conflict = conflictRepo.findById(conflictId)
                .orElseThrow(() -> new NotFoundException("Conflict %d not found".formatted(conflictId)));

        event.setConflict(conflict);
        return eventRepo.save(event);
    }

    public Event update(Long id, Event updated, Long conflictId) {
        Event existing = findById(id);

        existing.setEventDate(updated.getEventDate());
        existing.setLocation(updated.getLocation());
        existing.setDescription(updated.getDescription());

        if (conflictId != null) {
            Conflict conflict = conflictRepo.findById(conflictId)
                    .orElseThrow(() -> new NotFoundException("Conflict %d not found".formatted(conflictId)));
            existing.setConflict(conflict);
        }

        return existing;
    }

    public void delete(Long id) {
        eventRepo.deleteById(id);
    }
}
