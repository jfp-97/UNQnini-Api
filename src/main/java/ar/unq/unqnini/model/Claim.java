package ar.unq.unqnini.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(value = "Claims")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    @Id
    @NotNull
    private String productID;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String textContent;
}
