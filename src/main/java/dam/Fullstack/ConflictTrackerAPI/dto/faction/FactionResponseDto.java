package dam.Fullstack.ConflictTrackerAPI.dto.faction;

import java.util.List;

public record FactionResponseDto(
        Long id,
        String name,
        Long conflictId,
        List<String> supporterCountryCodes
) {}
