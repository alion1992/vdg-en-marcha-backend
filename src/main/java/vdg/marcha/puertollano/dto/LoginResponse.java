package vdg.marcha.puertollano.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private Long id;

    private String dni;

    private String nombre;

    private String token;

    private String apellidos;

    private String email;

    private String curso;

    private String rol;

}
