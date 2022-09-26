package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.DataOfOrder;
import ar.unq.unqnini.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<DataOfOrder, String> {
}
