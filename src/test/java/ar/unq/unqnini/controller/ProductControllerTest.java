package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product productOne;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productOne = new Product("P_1","POne", "", 50.00, Collections.singletonList("P1"), 100);
    }

    @Test
    void getProductsCaseThereAreProducts() {
        List<Product> listToReturn = Collections.singletonList(productOne);
        when(productService.getAllProducts()).thenReturn(listToReturn);
        assertEquals(listToReturn, productController.getProducts());
    }

    @Test
    void getProductsCaseNoThereAreProducts() {
        when(productService.getAllProducts()).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Service not available"));
        assertThrowsExactly(ResponseStatusException.class, () -> productController.getProducts(), "Service not available");
    }

    @Test
    void getProductByExistingID() {
        when(productService.getProduct("P_1")).thenReturn(Optional.of(productOne));
        assertEquals(productOne, productController.getProduct("P_1"));
    }

    @Test
    void getProductByNonexistentId() {
        when(productService.getProduct("P_X")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "The product does not exist"));
        assertThrowsExactly(ResponseStatusException.class, () -> productController.getProduct("P_1"), "The product does not exist");
    }
}