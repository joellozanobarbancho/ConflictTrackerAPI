package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.dto.faction.*;
import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.model.Country;
import dam.Fullstack.ConflictTrackerAPI.model.Faction;
import dam.Fullstack.ConflictTrackerAPI.repository.ConflictRepository;
import dam.Fullstack.ConflictTrackerAPI.repository.CountryRepository;
import dam.Fullstack.ConflictTrackerAPI.repository.FactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public FactionService(FactionRepository factionRepository,
                          ConflictRepository conflictRepository,
                          CountryRepository countryRepository) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    public List<FactionDTO> getAllFactions() {
        return factionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FactionDTO getFactionById(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException
("Faction not found with id: " + id));
        return toDTO(faction);
    }

    public List<FactionDTO> getFactionsByConflictId(Long conflictId) {
        return factionRepository.findByConflictId(conflictId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FactionDTO createFaction(FactionCreateDTO createDTO) {
        Conflict conflict = conflictRepository.findById(createDTO.conflictId())
                .orElseThrow(() -> new NotFoundException
("Conflict not found with id: " + createDTO.conflictId()));

        Faction faction = new Faction();
        faction.setName(createDTO.name());
        faction.setConflict(conflict);

        if (createDTO.supporterCountryCodes() != null && !createDTO.supporterCountryCodes().isEmpty()) {
            Set<Country> countries = new HashSet<>();
            for (String code : createDTO.supporterCountryCodes()) {
                Country country = countryRepository.findByCode(code)
                        .orElseThrow(() -> new NotFoundException
("Country not found with code: " + code));
                countries.add(country);
            }
            faction.setSupportingCountries(countries);
        }

        Faction saved = factionRepository.save(faction);
        return toDTO(saved);
    }

    public FactionDTO updateFaction(Long id, FactionCreateDTO updateDTO) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException
("Faction not found with id: " + id));

        Conflict conflict = conflictRepository.findById(updateDTO.conflictId())
                .orElseThrow(() -> new NotFoundException
("Conflict not found with id: " + updateDTO.conflictId()));

        faction.setName(updateDTO.name());
        faction.setConflict(conflict);

        if (updateDTO.supporterCountryCodes() != null) {
            Set<Country> countries = new HashSet<>();
            for (String code : updateDTO.supporterCountryCodes()) {
                Country country = countryRepository.findByCode(code)
                        .orElseThrow(() -> new NotFoundException
("Country not found with code: " + code));
                countries.add(country);
            }
            faction.setSupportingCountries(countries);
        }

        Faction saved = factionRepository.save(faction);
        return toDTO(saved);
    }

    public void deleteFaction(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new NotFoundException
("Faction not found with id: " + id);
        }
        factionRepository.deleteById(id);
    }

    private FactionDTO toDTO(Faction faction) {
        Set<String> countryCodes = faction.getSupportingCountries().stream()
                .map(Country::getCode)
                .collect(Collectors.toSet());

        return new FactionDTO(
                faction.getId(),
                faction.getName(),
                faction.getConflict().getId(),
                faction.getConflict().getName(),
                countryCodes
        );
    }
}