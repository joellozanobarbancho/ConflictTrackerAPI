package dam.Fullstack.ConflictTrackerAPI.dto;

import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;

import java.time.LocalDate;
import java.util.List;

public record ConflictDto(
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        List<String> countries
) {}

