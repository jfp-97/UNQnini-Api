package ar.unq.unqnini.model.order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Document(value = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreditCard extends OrderCard {

    @NotNull
    private String amountOfPayments;
}
