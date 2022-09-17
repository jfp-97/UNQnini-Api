package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() { return productService.getAllProducts(); }
}
