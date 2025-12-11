package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.model.*;
import dam.Fullstack.ConflictTrackerAPI.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ConflictService {
    private final ConflictRepository conflictRepo;
    private final CountryRepository countryRepo;

    public ConflictService(ConflictRepository conflictRepo, CountryRepository countryRepo) {
        this.conflictRepo = conflictRepo; this.countryRepo = countryRepo;
    }

    public List<Conflict> findAll() { return conflictRepo.findAll(); }
    public Conflict findById(Long id) { return conflictRepo.findById(id).orElseThrow(() -> new NotFoundException("Conflict %d not found".formatted(id))); }
    public List<Conflict> findByStatus(ConflictStatus status) { return conflictRepo.findByStatus(status); }
    public List<Conflict> findByCountryCode(String code) { return conflictRepo.findByCountryCode(code); }

    public Conflict create(Conflict conflict, List<String> countryCodes) {
        conflict.getCountries().clear();
        for (String code : countryCodes) {
            Country c = countryRepo.findByCode(code).orElseThrow(() -> new NotFoundException("Country %s not found".formatted(code)));
            conflict.getCountries().add(c);
        }
        return conflictRepo.save(conflict);
    }

    public Conflict update(Long id, Conflict updated, List<String> countryCodes) {
        Conflict existing = findById(id);
        existing.setName(updated.getName());
        existing.setStartDate(updated.getStartDate());
        existing.setStatus(updated.getStatus());
        existing.setDescription(updated.getDescription());
        existing.getCountries().clear();
        for (String code : countryCodes) {
            Country c = countryRepo.findByCode(code).orElseThrow(() -> new NotFoundException("Country %s not found".formatted(code)));
            existing.getCountries().add(c);
        }
        return existing;
    }

    public void delete(Long id) { conflictRepo.deleteById(id); }
}

class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}