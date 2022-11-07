package ar.unq.unqnini.model.order;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Document(value = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCash extends Order {

    @NotNull
    private Boolean paysExactAmount;

    @Nullable
    private Double payAmount;
}
