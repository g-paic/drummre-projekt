package project.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import project.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> getUserByUsername(String username);
}
