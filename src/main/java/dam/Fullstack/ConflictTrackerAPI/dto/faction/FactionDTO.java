package dam.Fullstack.ConflictTrackerAPI.dto.faction;

import java.util.List;
import java.util.Set;

public record FactionDTO(
        Long id,
        String name,
        Long conflictId,
        String conflictName,
        Set<String> supporterCountryCodes
) {}
