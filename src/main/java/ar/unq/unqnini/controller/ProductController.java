package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }
}
