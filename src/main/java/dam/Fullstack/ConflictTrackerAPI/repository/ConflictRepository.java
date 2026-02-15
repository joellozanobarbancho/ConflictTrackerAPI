package dam.Fullstack.ConflictTrackerAPI.repository;//package dam.Fullstack.ConflictTrackerAPI.mapper;

import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    List<Conflict> findByStatus(ConflictStatus status);

    @Query("SELECT DISTINCT c FROM Conflict c JOIN c.countries co WHERE co.code = :countryCode")
    List<Conflict> findByCountryCode(@Param("countryCode") String countryCode);

    List<Conflict> findByNameContainingIgnoreCase(String name);
}
