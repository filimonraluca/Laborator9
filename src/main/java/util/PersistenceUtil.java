package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Aceasta clasa contine un singur atribut de tipul EntityManagerFactory care este necesare pentru a crea in mod eficient
 * mai multe obiecte de tip EntityManager ce reprezinta efectiv conexiunea la baza de date.
 */
public class PersistenceUtil {
    private EntityManagerFactory entityManagerFactory = null;

    public PersistenceUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close(){
        entityManagerFactory.close();
    }
}
