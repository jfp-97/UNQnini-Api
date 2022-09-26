package ar.unq.unqnini.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseData {
    @Id
    private String id;
    private String paymentType;
    private Map<String, Integer> products;
}
