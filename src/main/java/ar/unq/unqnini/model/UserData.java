package ar.unq.unqnini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(value = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    @NotNull
    private String username;
    private String password;
    private String name;
    private Number cuit;
    private String businessName;
    private String businessAddress;

}
