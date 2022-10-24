package ar.unq.unqnini.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Coupons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    private String id;
    private int percentage;
}
