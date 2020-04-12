package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    EntityManagerFactory entityManagerFactory;

    public ArtistRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

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

    public Artist findById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "select a  from Artist a where a.id=:custId";

        TypedQuery<Artist> typedQuery = entityManager.createQuery(query, Artist.class);
        typedQuery.setParameter("custId",id);
        Artist artist = null;
        try{
            artist = typedQuery.getSingleResult();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return artist;
    }

    public List<Artist> findByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Artist.findByName", Artist.class);
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
