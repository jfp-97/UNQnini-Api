package ar.unq.unqnini.service;
import ar.unq.unqnini.model.Username;
import ar.unq.unqnini.model.order.purchase.DataOfOrder;
import ar.unq.unqnini.model.order.Order;
import ar.unq.unqnini.model.Product;
import ar.unq.unqnini.model.order.purchase.PurchaseData;
import ar.unq.unqnini.model.order.purchase.PurchaseId;
import ar.unq.unqnini.model.order.purchase.PurchaseProductBasicData;
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

    public void processOrder(Order order, ProductService productService) {
        List<Product> products = productRepository.findByIdIn(List.copyOf(order.getProducts().keySet()));

        List<Product> productsWithoutEnoughStock = products.stream().filter(product ->
                product.getStock() < order.getProducts().get(product.getId())).collect(Collectors.toList());

        if (!productsWithoutEnoughStock.isEmpty()) {
                throw new RuntimeException(productsWithoutEnoughStock.stream().map(product ->
                        "No hay suficiente " + product.getName() + ", se intento comprar " + order.getProducts().get(product.getId())
                                + " pero solo hay " + product.getStock() + " disponible; "
                ).reduce("", String::concat));
        } else {
            saveOrUpdateOrder(order, productService);
        }
    }

    public List<PurchaseData> getPurchases(Username username) {
        Optional<DataOfOrder> dataOfOrder = orderRepository.findById(username.getUsername());

        if(dataOfOrder.isPresent()) {
            return dataOfOrder.get().getPurchaseData();
        } else {
            return new ArrayList<>();
        }
    }

    private void saveOrUpdateOrder(Order order, ProductService productService) {
        String id = (order.getUsername().isBlank()) ? order.getCuit() : order.getUsername();
        Optional<DataOfOrder> dataOfOrder = orderRepository.findById(id);
        List<PurchaseData> purchases;

        String localDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss").format(LocalDateTime.now());

        if (dataOfOrder.isPresent()) {
            purchases = dataOfOrder.get().getPurchaseData();
        } else {
            purchases = new ArrayList<>();
        }

        List<PurchaseProductBasicData> purchaseProductBasicData = new ArrayList<>();
        order.getProducts().forEach((productID, count) ->  purchaseProductBasicData.add(productService.getBasicDataOfProduct(productID, count)));
        purchases.add(new PurchaseData(localDateTime, order.getClass().toString().substring(27), purchaseProductBasicData, order.getDiscount(), order.getTotal()));
        orderRepository.save(new DataOfOrder(id , purchases));
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
