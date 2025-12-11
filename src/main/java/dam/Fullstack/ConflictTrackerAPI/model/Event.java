package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long        id;
    @Column(nullable = false)
    private LocalDate   eventDate;
    @Column(nullable = false)
    private String      location;
    @Column(nullable = false)
    private String      description;

    //relation many to one Conflict
    @ManyToOne(optional = false)
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    public Event() {
    }

    public Event(LocalDate eventDate, String location, String description) {
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }
}
