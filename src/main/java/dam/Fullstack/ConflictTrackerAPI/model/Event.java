package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long        id;
    @Column(name = "event_date")
    private LocalDate   eventDate;
    @Column(name = "event_location")
    private String      location;
    @Column(name = "event_description")
    private String      description;

    //relation many to one Conflict
    @OneToMany(
            mappedBy = "conflict",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    ArrayList<Conflict> conflicts;
    public void addConflict(Conflict conflict) {
        conflicts.add(conflict);
//      conflicts.setEvent(this);
    }

    public Event(long id, LocalDate eventDate, String location, String description) {
        this.id = id;
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

    public ArrayList<Conflict> getConflicts() {
        return conflicts;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventDate=" + eventDate +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", conflicts=" + conflicts +
                '}';
    }
}
