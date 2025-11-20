package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "factions")
public class Faction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long        id;
    @Column(name = "fact_name", nullable = false)
    private String      name;

    //relations many to many conflict
    //relations many to many country
}
