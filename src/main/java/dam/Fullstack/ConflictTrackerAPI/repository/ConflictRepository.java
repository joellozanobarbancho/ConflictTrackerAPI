package dam.Fullstack.ConflictTrackerAPI.repository;

import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Long> {
    List<Conflict> findByStatus(ConflictStatus status);

    @Query("select distinct c from Conflict c join c.countries cc where cc.code = :code")
    List<Conflict> findByCountryCode(String code);
}
