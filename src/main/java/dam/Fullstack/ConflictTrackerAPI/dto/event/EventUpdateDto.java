package dam.Fullstack.ConflictTrackerAPI.dto.event;

import java.time.LocalDate;

public record EventUpdateDto(
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId
) {}
