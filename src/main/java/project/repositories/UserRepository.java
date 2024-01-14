package project.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> getUserByUsername(String username);

    User findByUsername(String userName);
}
