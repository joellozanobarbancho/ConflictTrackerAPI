package dam.Fullstack.ConflictTrackerAPI.controller;

import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryCreateDTO;
import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryDTO;
import dam.Fullstack.ConflictTrackerAPI.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryCreateDTO createDTO) {
        CountryDTO created = countryService.createCountry(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(
            @PathVariable Long id,
            @RequestBody CountryCreateDTO updateDTO) {
        CountryDTO updated = countryService.updateCountry(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable String code) {
        return ResponseEntity.ok(countryService.getCountryByCode(code));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CountryDTO> getCountryByName(@PathVariable String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

}
