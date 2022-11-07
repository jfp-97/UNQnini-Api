package ar.unq.unqnini.service;
import ar.unq.unqnini.model.Username;
import ar.unq.unqnini.model.order.purchase.DataOfOrder;
import ar.unq.unqnini.model.order.purchase.PurchaseData;
import ar.unq.unqnini.model.order.purchase.PurchaseProductBasicData;
import ar.unq.unqnini.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Username username;
    private Username nonexistentUser;
    private PurchaseData purchaseData;
    private List<PurchaseData> purchasesData;
    private DataOfOrder dataOfOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<PurchaseProductBasicData> purchasesBasicData = new ArrayList<>();
        PurchaseProductBasicData purchase_Uno = PurchaseProductBasicData.builder().id("01").name("FIGURITAS QATAR 2022").count(5).build();
        PurchaseProductBasicData purchase_Dos = PurchaseProductBasicData.builder().id("02").name("ALBUM QATAR 2022").count(2).build();
        purchasesBasicData.add(purchase_Uno);
        purchasesBasicData.add(purchase_Dos);
        purchaseData = PurchaseData.builder().id("TEST").paymentType("Efectivo").purchasesProducts(purchasesBasicData).discount(30).total(3000F).build();
        purchasesData = new ArrayList<>();
        purchasesData.add(purchaseData);
        username = Username.builder().username("Juan").build();
        nonexistentUser = Username.builder().username("X").build();
        dataOfOrder = DataOfOrder.builder().id("TEST").purchaseData(purchasesData).build();
    }


    @Test
    void getPurchases() {
        when(orderRepository.findById(username.getUsername())).thenReturn(Optional.of(dataOfOrder));
        assertTrue(orderService.getPurchases(username).contains(purchaseData));
        verify(orderRepository, times(1)).findById(username.getUsername());
    }

    @Test
    void getPurchasesWithNonexistentUser() {
        when(orderRepository.findById(nonexistentUser.getUsername())).thenReturn(Optional.empty());
        assertEquals(orderService.getPurchases(nonexistentUser), new ArrayList<>());
        verify(orderRepository, times(1)).findById(nonexistentUser.getUsername());
    }
}
