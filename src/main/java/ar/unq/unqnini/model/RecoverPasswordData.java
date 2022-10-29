package ar.unq.unqnini.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(value = "UserDatas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoverPasswordData {
    @Id
    @NotNull
    @NotBlank
    private String userName;
}