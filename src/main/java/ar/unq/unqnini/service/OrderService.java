package ar.unq.unqnini.service;
import ar.unq.unqnini.model.DataOfOrder;
import ar.unq.unqnini.model.Order;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.model.PurchaseData;
import ar.unq.unqnini.repository.OrderRepository;
import ar.unq.unqnini.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public void processOrder(Order order) {
        List<Product> products = productRepository.findByIdIn(List.copyOf(order.getProducts().keySet()));

        List<Product> productsWithoutEnoughStock = products.stream().filter(product ->
                product.getStock() < order.getProducts().get(product.getId())).collect(Collectors.toList());

        if (!productsWithoutEnoughStock.isEmpty()) {
                throw new RuntimeException(productsWithoutEnoughStock.stream().map(product ->
                        "No hay suficiente " + product.getName() + ", se intento comprar " + order.getProducts().get(product.getId())
                                + " pero solo hay " + product.getStock() + " disponible; "
                ).reduce("", String::concat));
        } else {
            saveOrUpdateOrder(order);
        }
    }

    private void saveOrUpdateOrder(Order order) {
        Optional<DataOfOrder> dataOfOrder = orderRepository.findById(order.getCuit());
        List<PurchaseData> purchases;
        String localDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now());

        if (dataOfOrder.isPresent()) {
            purchases = dataOfOrder.get().getPurchaseData();
        } else {
            purchases = new ArrayList<>();
        }
        purchases.add(new PurchaseData(localDateTime, order.getClass().toString().substring(27), order.getProducts()));
        orderRepository.save(new DataOfOrder(order.getCuit(), purchases, order.getDiscount()));
        decrementStock(order);
    }

    private void decrementStock(Order order) {
        for (var entry : order.getProducts().entrySet()) {
            Product product = productRepository.findById(entry.getKey()).get();
            product.setStock(product.getStock() - entry.getValue());
            productRepository.save(product);
        }
    }
}
