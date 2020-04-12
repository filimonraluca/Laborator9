package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
