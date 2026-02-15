package dam.Fullstack.ConflictTrackerAPI.dto.event;

import java.time.LocalDate;

public record EventCreateDTO(
        LocalDate eventDate,
        String location,
        String description,
        Long conflictId
) {}
