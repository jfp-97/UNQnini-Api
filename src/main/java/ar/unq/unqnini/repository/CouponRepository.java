package ar.unq.unqnini.repository;
import ar.unq.unqnini.model.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, String> {
    Optional<Coupon> findById(String id);
}