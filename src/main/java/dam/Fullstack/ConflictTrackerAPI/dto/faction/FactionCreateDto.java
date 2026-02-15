package dam.Fullstack.ConflictTrackerAPI.dto.faction;

import java.util.List;

public record FactionCreateDTO(
        String name,
        Long conflictId,
        List<String> supporterCountryCodes
) {}
