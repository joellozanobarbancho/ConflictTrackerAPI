package dam.Fullstack.ConflictTrackerAPI.dto.event;

import java.time.LocalDate;

public record EventResponseDto(
        Long id,
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId
) {}
