package vdg.marcha.puertollano.dto;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {

    private Long id;

    private String dni;

    private String nombre;

    private String apellidos;

    private String email;

    private String curso;

    private String rol;
}