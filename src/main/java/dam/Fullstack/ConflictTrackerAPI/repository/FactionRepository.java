package dam.Fullstack.ConflictTrackerAPI.repository;

import dam.Fullstack.ConflictTrackerAPI.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionRepository extends JpaRepository<Faction, Long> { }
