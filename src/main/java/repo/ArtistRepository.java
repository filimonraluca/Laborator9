package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa ArtistRepository are un singur atribut de tipul EntityManagerFactory necesare crearii obiectelor de
 * tipul EntityManager care reprezinta efectiva conexine la baza de date.
 */
public class ArtistRepository {
    EntityManagerFactory entityManagerFactory;

    public ArtistRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * In metoda Create inseram un nou artist in baza de date. Cream un obiect de tipul EntityManager si deoarece
     * inserarea unui nou album aduce modificari in baza da date este necesara folosirea unui obiect de tipul EntityTransaction
     * In block-ul try se creaza un obiect de tipul EntityTransaction si se incearca inserarea efectiva a albumului in baza de date.
     * In cazul in care apare o exceptie de apeleaza metoda rollback pentru a reveni la valorile anterioare din baza de date, iar
     * in block-ul finally se inchide conexiunea.
     * @param artist
     */
    public void create(Artist artist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(artist);
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
    public Artist findById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Artist artist = null;
        try{
            artist = entityManager.find(Artist.class, id);

        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return artist;
    }

    /**
     * In metoda findByName se creaza un obiect de tipul EntityManager. Metodei createNamedQuery primeste ca parametru
     * numele unui query si returneaza un obiect de tip query din care putem prelua rezultatele interogarii folosind
     * metoda getResultList(). Aceste rezultate le preluam intr-o lista de obiecte de tip Album.
     * @param name
     * @return
     */
    public List<Artist> findByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Artist.findByName");
        query.setParameter("name",name);
        List<Artist> artists = new ArrayList<>();
        try{
            artists = query.getResultList();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return artists;
    }
}
