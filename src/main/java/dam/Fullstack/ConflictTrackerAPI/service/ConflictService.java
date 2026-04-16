package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.dto.conflict.*;
import dam.Fullstack.ConflictTrackerAPI.dto.event.*;
import dam.Fullstack.ConflictTrackerAPI.dto.faction.*;
import dam.Fullstack.ConflictTrackerAPI.dto.country.*;
import dam.Fullstack.ConflictTrackerAPI.model.*;
import dam.Fullstack.ConflictTrackerAPI.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConflictService {

    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public ConflictService(ConflictRepository conflictRepository, CountryRepository countryRepository) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    public List<ConflictDTO> getAllConflicts() {
        return conflictRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ConflictDTO> getConflictsByStatus(ConflictStatus status) {
        return conflictRepository.findByStatus(status).stream()
                .map(this::toDTO)
                .toList();
    }

    public ConflictDetailDTO getConflictById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conflict not found with id: " + id));
        return toDetailDTO(conflict);
    }

    public ConflictDTO createConflict(ConflictCreateDTO createDTO) {
        Conflict conflict = new Conflict();
        conflict.setName(createDTO.getName());
        conflict.setStartDate(createDTO.getStartDate());
        conflict.setStatus(createDTO.getStatus());
        conflict.setDescription(createDTO.getDescription());

        if (createDTO.getCountryCodes() != null) {
            Set<Country> countries = createDTO.getCountryCodes().stream()
                    .map(code -> countryRepository.findByCode(code)
                            .orElseThrow(() -> new NotFoundException("Country not found with code: " + code)))
                    .collect(Collectors.toSet());
            conflict.setCountries(countries);
        }

        Conflict saved = conflictRepository.save(conflict);
        return toDTO(saved);
    }

    public ConflictDTO updateConflict(Long id, ConflictCreateDTO updateDTO) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conflict not found with id: " + id));

        conflict.setName(updateDTO.getName());
        conflict.setStartDate(updateDTO.getStartDate());
        conflict.setStatus(updateDTO.getStatus());
        conflict.setDescription(updateDTO.getDescription());

        if (updateDTO.getCountryCodes() != null) {
            Set<Country> countries = updateDTO.getCountryCodes().stream()
                    .map(code -> countryRepository.findByCode(code)
                            .orElseThrow(() -> new NotFoundException("Country not found with code: " + code)))
                    .collect(Collectors.toSet());
            conflict.setCountries(countries);
        }

        Conflict saved = conflictRepository.save(conflict);
        return toDTO(saved);
    }

    public void deleteConflict(Long id) {
        if (!conflictRepository.existsById(id)) {
            throw new NotFoundException("Conflict not found with id: " + id);
        }
        conflictRepository.deleteById(id);
    }

    public List<ConflictDTO> getConflictsByCountryCode(String countryCode) {
        return conflictRepository.findByCountryCode(countryCode).stream()
                .map(this::toDTO)
                .toList();
    }

    private ConflictDTO toDTO(Conflict conflict) {
        Set<String> countryCodes =
                safeSet(conflict.getCountries()).stream()
                        .map(Country::getCode)
                        .collect(Collectors.toSet());

        return new ConflictDTO(
                conflict.getId(),
                conflict.getName(),
                conflict.getStartDate(),
                conflict.getStatus(),
                conflict.getDescription(),
                countryCodes
        );
    }

    private ConflictDetailDTO toDetailDTO(Conflict conflict) {
        Set<CountryDTO> countries =
                safeSet(conflict.getCountries()).stream()
                        .map(c -> new CountryDTO(c.getId(), c.getName(), c.getCode()))
                        .collect(Collectors.toSet());

        List<FactionDTO> factions =
                safeList(conflict.getFactions()).stream()
                        .map(f -> new FactionDTO(
                                f.getId(),
                                f.getName(),
                                conflict.getId(),
                                conflict.getName(),
                                safeSet(f.getSupportingCountries()).stream()
                                        .map(Country::getCode)
                                        .collect(Collectors.toSet())
                        ))
                        .toList();

        List<EventDTO> events =
                safeList(conflict.getEvents()).stream()
                        .map(e -> new EventDTO(
                                e.getId(),
                                e.getEventDate(),
                                e.getLocation(),
                                e.getDescription(),
                                conflict.getId(),
                                conflict.getName()
                        ))
                        .toList();

        return new ConflictDetailDTO(
                conflict.getId(),
                conflict.getName(),
                conflict.getStartDate(),
                conflict.getStatus(),
                conflict.getDescription(),
                countries,
                factions,
                events
        );
    }

    private <T> Set<T> safeSet(Set<T> set) {
        return set == null ? Set.of() : set;
    }

    private <T> List<T> safeList(List<T> list) {
        return list == null ? List.of() : list;
    }
}
