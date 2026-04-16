package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "conflicts")
public class Conflict {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "city_date")
    private LocalDate startDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConflictStatus status;
    @Column(name = "city_description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "conflict_countries",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries = new HashSet<>();

    @OneToMany(mappedBy = "conflict", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Faction> factions = new ArrayList<>();

    @OneToMany(mappedBy = "conflict", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    public Conflict() {}

    public Conflict(String name, LocalDate startDate, ConflictStatus status, String description) {
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
