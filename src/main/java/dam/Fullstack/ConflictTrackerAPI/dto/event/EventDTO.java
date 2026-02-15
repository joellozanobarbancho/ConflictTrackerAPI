package dam.Fullstack.ConflictTrackerAPI.dto.event;

import java.time.LocalDate;

public record EventDTO(
        Long id,
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId,
        String conflictName
) {}
