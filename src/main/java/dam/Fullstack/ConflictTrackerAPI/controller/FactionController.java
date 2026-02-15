package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.faction.*;
import dam.Fullstack.ConflictTrackerAPI.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public ResponseEntity<List<FactionDTO>> getAllFactions(
            @RequestParam(required = false) Long conflictId) {
        List<FactionDTO> factions;
        if (conflictId != null) {
            factions = factionService.getFactionsByConflictId(conflictId);
        } else {
            factions = factionService.getAllFactions();
        }
        return ResponseEntity.ok(factions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFactionById(@PathVariable Long id) {
        FactionDTO faction = factionService.getFactionById(id);
        return ResponseEntity.ok(faction);
    }

    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody FactionCreateDTO createDTO) {
        FactionDTO created = factionService.createFaction(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(
            @PathVariable Long id,
            @RequestBody FactionCreateDTO updateDTO) {
        FactionDTO updated = factionService.updateFaction(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.deleteFaction(id);
        return ResponseEntity.noContent().build();
    }
}