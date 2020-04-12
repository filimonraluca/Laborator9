package entity;


import javax.persistence.*;

@Entity
@Table(name = "albums")
@NamedQueries(
        {@NamedQuery(name = "Album.findByName",
                query = "SELECT p FROM Album p WHERE p.name=:name"),
                @NamedQuery(name = "Album.findByArtist",
                        query = "SELECT p FROM Album p WHERE p.artist_id=:artist_id")})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "artist_id")
    private int artist_id;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Basic(optional = false)
    @Column(name = "RELEASE_YEAR")
    private int release_year;

    public Album(String name, int artist_id, int release_year) {
        this.name = name;
        this.artist_id = artist_id;
        this.release_year = release_year;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", artist_id=" + artist_id +
                ", name='" + name + '\'' +
                ", release_year=" + release_year +
                '}';
    }
}
