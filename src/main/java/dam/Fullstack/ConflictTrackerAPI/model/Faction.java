package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "factions")
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long        id;
    @Column(nullable = false)
    private String      name;

    //relations many to many conflict
    //relations many to many country
    @ManyToOne(optional = false)
    @JoinColumn(name = "conflict_id")
    private Conflict  conflict;

    @ManyToMany
    @JoinTable(name = "faction_countries",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    ) private Set<Country> countries = new HashSet<>();

    public Faction() {
    }

    public Faction(String name, Conflict conflict) {
        this.name = name;
        this.conflict = conflict;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }
}
