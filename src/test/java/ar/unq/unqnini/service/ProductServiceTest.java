package ar.unq.unqnini.service;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product productOne;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productOne = new Product("P_1","POne", "", 50.00, Collections.singletonList("P1"), 100);
    }

    @Test
    void getAllProducts() {
        List<Product> listToReturn = Collections.singletonList(productOne);
        when(productRepository.findAll()).thenReturn(listToReturn);
        assertEquals(listToReturn, productService.getAllProducts());
    }

    @Test
    void getProduct() {
        when(productRepository.findById("P_1")).thenReturn(Optional.ofNullable(productOne));
        assertEquals(Optional.ofNullable(productOne), productService.getProduct("P_1"));
    }
}