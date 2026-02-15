package dam.Fullstack.ConflictTrackerAPI.repository;

import dam.Fullstack.ConflictTrackerAPI.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByConflictId(Long conflictId);

    List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);

    List<Event> findByLocationContainingIgnoreCase(String location);

    List<Event> findByConflictIdOrderByEventDateDesc(Long conflictId);
}