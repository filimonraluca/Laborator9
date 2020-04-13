package entity;
import javax.persistence.*;

/**
 * Clasa Artist este o entitate, reprezentand tabela artists din baza de date si fiecare atribut
 * reprezinta o coloana din baza de date. Avem de asemenea un named query folosit pentru a defini
 * un query a carui string nu se modifica.
 */

@Entity
@Table(name="artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT p FROM Artist p WHERE p.name=:name")})

public class Artist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
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
