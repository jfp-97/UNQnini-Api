package ar.unq.unqnini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Document(value = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String fullname;


    @Pattern(regexp = "^\\d{11}$")
    @Pattern(regexp = "^[0-9]*$")
    private String cuit;

    @NotNull
    @NotBlank
    private String businessName;

    @NotNull
    @NotBlank
    private String businessAddress;

    private String pictureUrl;
}
