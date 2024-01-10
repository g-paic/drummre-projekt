package project.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.entities.TrackEntity;

public interface TrackRepository extends MongoRepository<TrackEntity, String> {
	public Optional<TrackEntity> getTrackByName(String name);
}
