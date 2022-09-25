package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByStockIsGreaterThan(Integer stock);

    List<Product> findByIdIn(List<String> idList);
}
