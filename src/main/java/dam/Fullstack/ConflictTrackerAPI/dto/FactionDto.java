package dam.Fullstack.ConflictTrackerAPI.dto;

import java.util.List;

public record FactionDto(
        String name,
        Long conflictId,
        List<String> supporterCountryCodes
) {}
