package dam.Fullstack.ConflictTrackerAPI.repository;

import dam.Fullstack.ConflictTrackerAPI.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }