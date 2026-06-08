package vdg.marcha.puertollano.service;



import vdg.marcha.puertollano.dto.RegistroRequest;
import lombok.RequiredArgsConstructor;
import vdg.marcha.puertollano.model.Rol;
import vdg.marcha.puertollano.model.Usuario;
import org.springframework.stereotype.Service;

import vdg.marcha.puertollano.repository.UsuarioRepository;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario registrar(RegistroRequest request) {

        Usuario usuario = Usuario.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(request.getPassword())
                .curso(request.getCurso())
                .rol(Rol.ALUMNO)
                .build();

        return usuarioRepository.save(usuario);
    }

}