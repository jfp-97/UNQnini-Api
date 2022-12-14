package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.service.ProductService;
import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() { return productService.getAllProducts(); }

    @GetMapping("/products/{productID}")
    public Product getProduct(@PathVariable String productID) {
            return productService.getProduct(productID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The product does not exist"));
    }
}
