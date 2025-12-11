package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conflicts")
public class Conflict {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long        id;
    @Column(nullable = false)
    private String      name;
    @Column(name = "city_date")
    private LocalDate   startDate;
    @Column(name = "city_description")
    private String      description;

    //relation many to many Country
    @ManyToMany
    @JoinTable(name = "conflict_countries",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    ) private Set<Country> countries = new  HashSet<>();

    public Conflict() {
    }

    public Conflict(String name, LocalDate startDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
