package dam.Fullstack.ConflictTrackerAPI.dto.event;

import java.time.LocalDate;

public record EventCreateDto(
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId
) {}
