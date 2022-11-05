package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.order.purchase.DataOfOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<DataOfOrder, String> {
}
