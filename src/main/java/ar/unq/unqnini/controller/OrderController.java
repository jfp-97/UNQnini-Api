package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Username;
import ar.unq.unqnini.model.order.OrderCard;
import ar.unq.unqnini.model.order.OrderCash;
import ar.unq.unqnini.model.order.OrderCreditCard;
import ar.unq.unqnini.model.order.purchase.PurchaseData;
import ar.unq.unqnini.model.order.purchase.PurchaseId;
import ar.unq.unqnini.service.OrderService;
import ar.unq.unqnini.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @PostMapping("/order/cash")
    public void placeOrderCash(@Valid @RequestBody OrderCash order) { orderService.processOrder(order,productService); }

    @PostMapping("/order/debitCard")
    public void placeOrderDebitCard(@Validated @RequestBody OrderCard order) {orderService.processOrder(order,productService); }

    @PostMapping("/order/creditCard")
    public void placeOrderCreditCard(@Validated @RequestBody OrderCreditCard order) { orderService.processOrder(order,productService); }

    @PostMapping("/order/getMyPurchases")
    @ResponseBody
    public List<PurchaseData> getPurchases(@Validated @RequestBody Username username) { return orderService.getPurchases(username); }
}
