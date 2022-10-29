package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoginRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findById(String id);
}
