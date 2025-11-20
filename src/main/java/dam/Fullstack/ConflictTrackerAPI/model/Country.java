package dam.Fullstack.ConflictTrackerAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long        id;
    @Column(name = "ctry_name", nullable = false)
    private String      name;
    @Column(name = "ctry_code", nullable = false, length = 3)
    private String      code;

    public Country(long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
