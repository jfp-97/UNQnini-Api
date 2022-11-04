package ar.unq.unqnini.controller;

import ar.unq.unqnini.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;


}
