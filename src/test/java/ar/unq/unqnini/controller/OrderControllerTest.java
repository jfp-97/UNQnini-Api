package ar.unq.unqnini.controller;
import ar.unq.unqnini.model.Username;
import ar.unq.unqnini.model.order.purchase.PurchaseData;
import ar.unq.unqnini.model.order.purchase.PurchaseProductBasicData;
import ar.unq.unqnini.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private Username username;
    private Username nonexistentUser;
    private PurchaseData purchaseData;
    private List<PurchaseData> purchasesData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<PurchaseProductBasicData> purchasesBasicData = new ArrayList<>();
        PurchaseProductBasicData purchase_Uno = PurchaseProductBasicData.builder().id("01").name("FIGURITAS QATAR 2022").count(5).build();
        PurchaseProductBasicData purchase_Dos = PurchaseProductBasicData.builder().id("02").name("ALBUM QATAR 2022").count(2).build();
        purchasesBasicData.add(purchase_Uno);
        purchasesBasicData.add(purchase_Dos);
        purchaseData = PurchaseData.builder().id("Compra_Juan").paymentType("Efectivo").purchasesProducts(purchasesBasicData).discount(30).total(3000F).build();
        purchasesData = new ArrayList<>();
        purchasesData.add(purchaseData);
        username = Username.builder().username("Juan").build();
        nonexistentUser = Username.builder().username("X").build();
    }

    @Test
    void getPurchases() {
        when(orderService.getPurchases(username)).thenReturn(purchasesData);
        assertTrue(orderController.getPurchases(username).contains(purchaseData));
        verify(orderService, times(1)).getPurchases(username);

    }

    @Test
    void getPurchasesWithNonexistentUser() {
        when(orderService.getPurchases(nonexistentUser)).thenReturn(new ArrayList<>());
        assertEquals(orderController.getPurchases(nonexistentUser), new ArrayList<>());
        verify(orderService, times(1)).getPurchases(nonexistentUser);
    }
}
