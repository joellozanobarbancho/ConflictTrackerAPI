package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.faction.*;
import dam.Fullstack.ConflictTrackerAPI.mapper.FactionMapper;
import dam.Fullstack.ConflictTrackerAPI.model.Faction;
import dam.Fullstack.ConflictTrackerAPI.service.FactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService service;
    private final FactionMapper mapper;

    public FactionController(FactionService service, FactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<FactionResponseDto> list() {
        return service.findAll().stream()
                .map(faction -> new FactionResponseDto(
                        faction.getId(),
                        faction.getName(),
                        faction.getConflict().getId(),
                        faction.getCountries().stream().map(c -> c.getCode()).toList()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public FactionResponseDto get(@PathVariable Long id) {
        Faction faction = service.findById(id);
        return new FactionResponseDto(
                faction.getId(),
                faction.getName(),
                faction.getConflict().getId(),
                faction.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @PostMapping
    public FactionResponseDto create(@RequestBody FactionCreateDto dto) {
        Faction faction = mapper.toEntity(dto);
        Faction saved = service.create(faction, dto.conflictId(), dto.supporterCountryCodes());
        return new FactionResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getConflict().getId(),
                saved.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @PutMapping("/{id}")
    public FactionResponseDto update(@PathVariable Long id, @RequestBody FactionUpdateDto dto) {
        Faction faction = mapper.toEntity(dto);
        Faction updated = service.update(id, faction, dto.supporterCountryCodes());
        return new FactionResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getConflict().getId(),
                updated.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
