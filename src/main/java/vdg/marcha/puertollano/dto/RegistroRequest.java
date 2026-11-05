package vdg.marcha.puertollano.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistroRequest {

    @NotBlank
    private String usuario;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @Email
    private String email;

    @NotBlank
    private String password;

}