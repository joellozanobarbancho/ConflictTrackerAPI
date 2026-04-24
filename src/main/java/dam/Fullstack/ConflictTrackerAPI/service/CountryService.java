package dam.Fullstack.ConflictTrackerAPI.service;

import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryCreateDTO;
import dam.Fullstack.ConflictTrackerAPI.dto.country.CountryDTO;
import dam.Fullstack.ConflictTrackerAPI.model.Country;
import dam.Fullstack.ConflictTrackerAPI.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country not found with id: " + id));
        return toDTO(country);
    }

    public CountryDTO createCountry(CountryCreateDTO createDTO) {

        if (countryRepository.existsByCode(createDTO.code())) {
            throw new IllegalArgumentException("Country with code " + createDTO.code() + " already exists");
        }

        Country country = new Country();
        country.setName(createDTO.name());
        country.setCode(createDTO.code());

        Country saved = countryRepository.save(country);
        return toDTO(saved);
    }

    public CountryDTO updateCountry(Long id, CountryCreateDTO updateDTO) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country not found with id: " + id));

        if (!country.getCode().equals(updateDTO.code())
                && countryRepository.existsByCode(updateDTO.code())) {
            throw new IllegalArgumentException("Country with code " + updateDTO.code() + " already exists");
        }

        country.setName(updateDTO.name());
        country.setCode(updateDTO.code());

        Country saved = countryRepository.save(country);
        return toDTO(saved);
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new NotFoundException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }

    private CountryDTO toDTO(Country country) {
        return new CountryDTO(
                country.getId(),
                country.getName(),
                country.getCode()
        );
    }

    public CountryDTO getCountryByCode(String code) {
        Country country = countryRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Country not found with code: " + code));
        return toDTO(country);
    }

    public CountryDTO getCountryByName(String name) {
        Country country = countryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Country not found with name: " + name));
        return toDTO(country);
    }

}
