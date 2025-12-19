package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.model.Conflict;
import dam.Fullstack.ConflictTrackerAPI.model.Country;
import dam.Fullstack.ConflictTrackerAPI.model.Faction;
import dam.Fullstack.ConflictTrackerAPI.repository.ConflictRepository;
import dam.Fullstack.ConflictTrackerAPI.repository.CountryRepository;
import dam.Fullstack.ConflictTrackerAPI.repository.FactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepo;
    private final ConflictRepository conflictRepo;
    private final CountryRepository countryRepo;

    public FactionService(FactionRepository factionRepo, ConflictRepository conflictRepo, CountryRepository countryRepo) {
        this.factionRepo = factionRepo;
        this.conflictRepo = conflictRepo;
        this.countryRepo = countryRepo;
    }

    public List<Faction> findAll() {
        return factionRepo.findAll();
    }

    public Faction findById(Long id) {
        return factionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Faction %d not found".formatted(id)));
    }

    public Faction create(Faction faction, Long conflictId, List<String> supporterCodes) {
        Conflict conflict = conflictRepo.findById(conflictId)
                .orElseThrow(() -> new NotFoundException("Conflict %d not found".formatted(conflictId)));

        faction.setConflict(conflict);

        faction.getCountries().clear();
        for (String code : supporterCodes) {
            Country c = countryRepo.findByCode(code)
                    .orElseThrow(() -> new NotFoundException("Country %s not found".formatted(code)));
            faction.getCountries().add(c);
        }

        return factionRepo.save(faction);
    }

    public Faction update(Long id, Faction updated, List<String> supporterCodes) {
        Faction existing = findById(id);

        existing.setName(updated.getName());

        existing.getCountries().clear();
        for (String code : supporterCodes) {
            Country c = countryRepo.findByCode(code)
                    .orElseThrow(() -> new NotFoundException("Country %s not found".formatted(code)));
            existing.getCountries().add(c);
        }

        return existing;
    }

    public void delete(Long id) {
        factionRepo.deleteById(id);
    }
}
