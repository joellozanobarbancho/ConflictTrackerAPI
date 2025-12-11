package dam.Fullstack.ConflictTrackerAPI.dto;

import java.time.LocalDate;

public record EventDto(
        Long id,
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId
) { }
