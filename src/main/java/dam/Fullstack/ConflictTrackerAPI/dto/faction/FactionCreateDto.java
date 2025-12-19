package dam.Fullstack.ConflictTrackerAPI.dto.faction;

import java.util.List;

public record FactionCreateDto(
        String name,
        Long conflictId,
        List<String> supporterCountryCodes
) {}
