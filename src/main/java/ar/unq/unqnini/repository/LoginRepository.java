package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.LoginData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoginRepository extends MongoRepository<LoginData, String> {
    Optional<LoginData> findById(String id);
}
