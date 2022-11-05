package ar.unq.unqnini.model.order.purchase;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseData {
    @Id
    private String id;
    private String paymentType;
    private List<PurchaseProductBasicData> purchasesProducts;
    private int discount;
    private Float total;
}
