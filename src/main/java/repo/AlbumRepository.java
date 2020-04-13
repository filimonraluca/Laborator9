package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa AlbumRepository are un singur atribut de tipul EntityManagerFactory necesare crearii obiectelor de
 * tipul EntityManager care reprezinta efectiva conexine la baza de date.
 */
public class AlbumRepository {
    EntityManagerFactory entityManagerFactory;

    public AlbumRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * In metoda Create inseram un nou album in baza de date. Cream un obiect de tipul EntityManager si deoarece
     * inserarea unui nou album aduce modificari in baza da date este necesara folosirea unui obiect de tipul EntityTransaction
     * In block-ul try se creaza un obiect de tipul EntityTransaction si se incearca inserarea efectiva a albumului in baza de date.
     * In cazul in care apare o exceptie de apeleaza metoda rollback pentru a reveni la valorile anterioare din baza de date, iar
     * in block-ul finally se inchide conexiunea.
     * @param album
     */
    public void create(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(album);
            entityTransaction.commit();
        }
        catch (Exception e){
            if (e!=null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * In metoda finById se creaza un oebict de tipul EntityManager si se foloseste metoda deja existena find pentru
     * gasirea unei entitati in baza de date dupa cheia sa primara. In block-ul finally se inchide conexiunea.
     * @param id
     * @return
     */
    public Album findById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Album album= null;
        try{
            album = entityManager.find(Album.class, id);
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return album;
    }

    /**
     * In metoda findByName se creaza un obiect de tipul EntityManager. Metodei createNamedQuery primeste ca parametru
     * numele unui query si returneaza un obiect de tip query din care putem prelua rezultatele interogarii folosind
     * metoda getResultList(). Aceste rezultate le preluam intr-o lista de obiecte de tip Album.
     * @param name
     * @return
     */
    public List<Album> findByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Album.findByName");
        query.setParameter("name",name);
        List<Album> albums = new ArrayList<>();
        try{
            albums = query.getResultList();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return albums;
    }

    /**
     * In metoda findByArtist se creaza un obiect de tipul EntityManager. Metodei createNamedQuery primeste ca parametru
     * numele unui query si returneaza un obiect de tip query din care putem prelua rezultatele interogarii folosind
     * metoda getResultList(). Aceste rezultate le preluam intr-o lista de obiecte de tip Album.
     * @param artist
     * @return
     */
    public List<Album> findByArtist(Artist artist){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Album.findByArtist");
        query.setParameter("artist_id",artist.getId());
        List<Album> albums = new ArrayList<>();
        try{
            albums = query.getResultList();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return albums;
    }

}
