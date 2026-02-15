package dam.Fullstack.ConflictTrackerAPI.dto.conflict;

import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryDTO;
import dam.Fullstack.ConflictTrackerAPI.dto.event.EventDTO;
import dam.Fullstack.ConflictTrackerAPI.dto.faction.FactionDTO;
import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ConflictDetailDTO(
        Long id,
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        Set<CountryDTO> countries,
        List<FactionDTO> factions,
        List<EventDTO> events
) {}
