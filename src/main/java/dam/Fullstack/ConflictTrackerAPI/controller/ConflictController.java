package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.conflict.*;
import dam.Fullstack.ConflictTrackerAPI.mapper.ConflictMapper;
import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService service;
    private final ConflictMapper mapper;

    public ConflictController(ConflictService service, ConflictMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ConflictResponseDto> list() {
        return service.findAll().stream()
                .map(conflict -> {
                    ConflictResponseDto dto = mapper.toResponseDto(conflict);
                    dto = new ConflictResponseDto(
                            conflict.getId(),
                            conflict.getName(),
                            conflict.getStartDate(),
                            conflict.getStatus(),
                            conflict.getDescription(),
                            conflict.getCountries().stream().map(c -> c.getCode()).toList()
                    );
                    return dto;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public ConflictResponseDto get(@PathVariable Long id) {
        Conflict conflict = service.findById(id);
        return new ConflictResponseDto(
                conflict.getId(),
                conflict.getName(),
                conflict.getStartDate(),
                conflict.getStatus(),
                conflict.getDescription(),
                conflict.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @PostMapping
    public ConflictResponseDto create(@RequestBody ConflictCreateDto dto) {
        Conflict conflict = mapper.toEntity(dto);
        Conflict saved = service.create(conflict, dto.countryCodes());
        return new ConflictResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getStartDate(),
                saved.getStatus(),
                saved.getDescription(),
                saved.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @PutMapping("/{id}")
    public ConflictResponseDto update(@PathVariable Long id, @RequestBody ConflictUpdateDto dto) {
        Conflict conflict = mapper.toEntity(dto);
        Conflict updated = service.update(id, conflict, dto.countryCodes());
        return new ConflictResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getStartDate(),
                updated.getStatus(),
                updated.getDescription(),
                updated.getCountries().stream().map(c -> c.getCode()).toList()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
