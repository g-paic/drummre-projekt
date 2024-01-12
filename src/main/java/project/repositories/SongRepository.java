package project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.entities.SongEntity;

import java.util.List;

@Repository
public interface SongRepository extends MongoRepository<SongEntity, String> {

    List<SongEntity> findAll();
}
