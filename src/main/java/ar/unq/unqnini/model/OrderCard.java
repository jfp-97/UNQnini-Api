package ar.unq.unqnini.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Document(value = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCard extends Order {

    @NotNull
    @NotBlank
    private String payerName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{16}$")
    private String cardNumber;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$")
    private String cardExpirationDate;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{3}$")
    private String cardSecurityCode;
}
