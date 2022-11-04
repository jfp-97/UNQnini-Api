package ar.unq.unqnini.repository;

import ar.unq.unqnini.model.PurchaseData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository  extends MongoRepository<PurchaseData, String> {

}
