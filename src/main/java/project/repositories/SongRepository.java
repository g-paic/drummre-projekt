package project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.entities.SongEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends MongoRepository<SongEntity, String> {

    List<SongEntity> findAll();

    List<SongEntity> findByOrderByEnergyDesc();



    List<SongEntity> findAllByMood(String mood);

    Optional<SongEntity> findBySpotifyId(String songId);

   List< SongEntity> findByArtist(String name);
}
