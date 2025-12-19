package dam.Fullstack.ConflictTrackerAPI.dto.faction;

import java.util.List;

public record FactionUpdateDto(
        String name,
        List<String> supporterCountryCodes
) {}
