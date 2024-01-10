package project.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.entities.DeezerEntity;

public interface DeezerRepository extends MongoRepository<DeezerEntity, String> {
	public Optional<DeezerEntity> getDeezerByTitle(String title);
}
