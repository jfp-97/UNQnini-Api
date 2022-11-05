package ar.unq.unqnini.model.order.purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProductBasicData {
    private String id;
    private String name;
    private Integer count;
}
