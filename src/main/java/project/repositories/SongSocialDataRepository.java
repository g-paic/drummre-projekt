package project.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.entities.SongSocialDataEntity;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SongSocialDataRepository extends MongoRepository<SongSocialDataEntity, String> {

//    public Page<SongSocialDataEntity> findAll(Pageable pageable);

    List<SongSocialDataEntity> findByName(String name);

//    List<SongSocialDataEntity> findAll();
}
