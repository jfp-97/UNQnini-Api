package ar.unq.unqnini.model.order;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Document(value = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Order {

    private String username;

    @NotNull
    @NotBlank
    private String buyerName;

    @NotNull
    @NotBlank
    private String businessName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String cuit;

    @NotNull
    @NotBlank
    private String businessAddress;

    @NotNull
    private Boolean isByHomeDelivery;

    @Nullable
    private String deliveryAddress;

    private Map<String, Integer> products;

    @Nullable
    private int discount;

    private Float total;
}
