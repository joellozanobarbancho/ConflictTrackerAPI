package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "conflicts")
public class Conflict {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long        id;
    @Column(name = "city_name", nullable = false)
    private String      name;
    @Column(name = "city_date")
    private LocalDate   startDate;
    @Column(name = "city_description")
    private String      description;

    //relation many to many Country
}
