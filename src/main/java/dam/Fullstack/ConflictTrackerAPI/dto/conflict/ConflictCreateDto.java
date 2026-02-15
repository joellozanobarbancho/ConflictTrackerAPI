package dam.Fullstack.ConflictTrackerAPI.dto.conflict;

import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;

import java.time.LocalDate;
import java.util.Set;

public record ConflictCreateDTO(
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        Set<String> countryCodes
) {}
