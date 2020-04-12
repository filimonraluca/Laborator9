package entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT p FROM Artist p WHERE p.name=:name")})

public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;

    public Integer getId() {
        return id;
    }

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Artist() {
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
