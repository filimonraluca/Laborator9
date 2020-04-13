package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import java.util.List;

public class AlbumManager {
    public static void main(String[] args) {
        PersistenceUtil persistenceUtil = new PersistenceUtil();

        Artist artist1 = new Artist("Raluca", "Italia");
        Artist artist2 = new Artist("Robert", "Rusia");
        Artist artist3 = new Artist("Maria", "Spania");
        Artist artist4 = new Artist("Maria", "Italia");
        Artist artist5 = new Artist("George", "Spania");
        ArtistRepository artistRepository = new ArtistRepository(persistenceUtil.getEntityManagerFactory());
        artistRepository.create(artist1);
        artistRepository.create(artist2);
        artistRepository.create(artist3);
        artistRepository.create(artist4);
        artistRepository.create(artist5);

        System.out.println("------Artist by id ---------");
        Artist findedArtistByID = artistRepository.findById(3);
        System.out.printf("Artist found by id %s\n",findedArtistByID.toString());

        System.out.println("------Artist by name ---------");
        List<Artist> findedArtistByName = artistRepository.findByName("Maria");
        for (Artist a : findedArtistByName) {
            System.out.printf("Artist found by name %s\n", a.toString());
        }

        Album album1 = new Album("titlu1",1, 1999);
        Album album2 = new Album("titlu2",1, 1990);
        Album album3 = new Album("titlu3",2, 2001);
        Album album4 = new Album("titlu1",3, 2010);
        Album album5 = new Album("titlu1",2, 2012);
        AlbumRepository albumRepository = new AlbumRepository(persistenceUtil.getEntityManagerFactory());
        albumRepository.create(album1);
        albumRepository.create(album2);
        albumRepository.create(album3);
        albumRepository.create(album4);
        albumRepository.create(album5);

        System.out.println("------Album by id ---------");
        Album albumFoundById = albumRepository.findById(3);
        System.out.printf("Album found by id %s\n", albumFoundById.toString());

        System.out.println("------Album by name ---------");
        List<Album> foundbyName = albumRepository.findByName("titlu1");
        for (Album a : foundbyName){
            System.out.printf("Album found by name %s\n", a.toString());
        }

        System.out.println("------Album by artist ---------");
        List<Album> foundbyArtist = albumRepository.findByArtist(artist1);
        for (Album a : foundbyArtist){
            System.out.printf("Album found by artist %s\n", a.toString());
        }
    }
}
