package ar.unq.unqnini.service;
import ar.unq.unqnini.dao.ProductDAO;
import ar.unq.unqnini.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductDAO productDAO;

    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
