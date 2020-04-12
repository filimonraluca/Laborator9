package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    EntityManagerFactory entityManagerFactory;

    public AlbumRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

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

    public Album findById(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "select a  from Album a where a.id=:custId";

        TypedQuery<Album> typedQuery = entityManager.createQuery(query, Album.class);
        typedQuery.setParameter("custId",id);
        Album album= null;
        try{
            album = typedQuery.getSingleResult();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return album;
    }

    public List<Album> findByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Album.findByName", Album.class);
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

    public List<Album> findByArtist(Artist artist){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("Album.findByArtist", Album.class);
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
