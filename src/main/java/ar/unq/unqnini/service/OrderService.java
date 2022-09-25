package ar.unq.unqnini.service;

import ar.unq.unqnini.model.Order;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    ProductRepository productRepository;

    public void processOrder(Order order) {
        List<Product> products = productRepository.findByIdIn(List.copyOf(order.getProducts().keySet()));
        List<Product> productsWithoutEnoughStock = products.stream().filter(product ->
                product.getStock() < order.getProducts().get(product.getId())).collect(Collectors.toList());

        if (!productsWithoutEnoughStock.isEmpty()) {

            throw new RuntimeException(productsWithoutEnoughStock.stream().map(product ->
                    "No hay suficiente " + product.getName() + ", se intento comprar " + order.getProducts().get(product.getId())
                    + " pero solo hay " + product.getStock() + " disponible; "
            ).reduce("", String::concat));
        }

    }
}
