package dam.Fullstack.ConflictTrackerAPI.dto.conflict;

import dam.Fullstack.ConflictTrackerAPI.model.ConflictStatus;

import java.time.LocalDate;
import java.util.Set;

public class ConflictCreateDTO {
    private String name;
    private LocalDate startDate;
    private ConflictStatus status;
    private String description;
    private Set<String> countryCodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(Set<String> countryCodes) {
        this.countryCodes = countryCodes;
    }

    public ConflictCreateDTO() {}

}
