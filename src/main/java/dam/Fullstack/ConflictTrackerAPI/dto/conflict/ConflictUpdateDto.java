package dam.Fullstack.ConflictTrackerAPI.dto.conflict;

import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;

import java.time.LocalDate;
import java.util.List;

public record ConflictUpdateDto(
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        List<String> countryCodes
) {}
