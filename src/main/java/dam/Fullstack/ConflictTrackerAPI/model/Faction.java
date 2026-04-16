package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "factions")
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    @ManyToMany
    @JoinTable(
            name = "faction_countries",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> supportingCountries = new HashSet<>();

    public Faction() {}

    public Faction(String name, Conflict conflict) {
        this.name = name;
        this.conflict = conflict;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public Set<Country> getSupportingCountries() {
        return supportingCountries;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }

    public void setSupportingCountries(Set<Country> supportingCountries) {
        this.supportingCountries = supportingCountries;
    }
}
