package ar.unq.unqnini.service;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.model.order.purchase.PurchaseProductBasicData;
import ar.unq.unqnini.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() { return productRepository.findByStockIsGreaterThan(0); }
    public Optional<Product> getProduct(String productID) { return productRepository.findById(productID); }
    public PurchaseProductBasicData getBasicDataOfProduct(String productID, Integer count) {
        Product product = productRepository.findById(productID).get();
        return new PurchaseProductBasicData(product.getId(), product.getName(), count);
    }
}
