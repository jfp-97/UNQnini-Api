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
        productOne = Product.builder()
            .id("P_1")
            .name("POne")
            .pictureUrl("")
            .price(50.00)
            .description(Collections.singletonList("P1"))
            .stock(100)
            .build();
    }

    @Test
    void getAllProducts() {
        List<Product> listToReturn = Collections.singletonList(productOne);
        when(productRepository.findByStockIsGreaterThan(0)).thenReturn(listToReturn);
        assertEquals(listToReturn, productService.getAllProducts());
    }

    @Test
    void getProduct() {
        when(productRepository.findById("P_1")).thenReturn(Optional.ofNullable(productOne));
        assertEquals(Optional.ofNullable(productOne), productService.getProduct("P_1"));
    }
}
