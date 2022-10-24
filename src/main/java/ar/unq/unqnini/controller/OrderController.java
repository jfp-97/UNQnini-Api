package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.OrderCard;
import ar.unq.unqnini.model.OrderCash;
import ar.unq.unqnini.model.OrderCreditCard;
import ar.unq.unqnini.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order/cash")
    public void placeOrderCash(@Valid @RequestBody OrderCash order) { orderService.processOrder(order); }

    @PostMapping("/order/debitCard")
    public void placeOrderDebitCard(@Validated @RequestBody OrderCard order) {orderService.processOrder(order); }

    @PostMapping("/order/creditCard")
    public void placeOrderCreditCard(@Validated @RequestBody OrderCreditCard order) { orderService.processOrder(order); }
}
