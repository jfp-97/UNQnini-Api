package ar.unq.unqnini.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(value = "Orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOfOrder {
    @Id
    private String id;
    private List<PurchaseData> purchaseData;
}
