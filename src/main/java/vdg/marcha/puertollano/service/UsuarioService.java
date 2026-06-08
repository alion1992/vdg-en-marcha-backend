package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vdg.marcha.puertollano.dto.RegistroRequest;
import vdg.marcha.puertollano.dto.UsuarioResponse;
import vdg.marcha.puertollano.model.Rol;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioResponse registrar(RegistroRequest request) {

        if (usuarioRepository.findByDni(request.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese DNI");
        }

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        Usuario usuario = Usuario.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .curso(request.getCurso())
                .rol(Rol.ALUMNO)
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponse.builder()
                .id(usuarioGuardado.getId())
                .dni(usuarioGuardado.getDni())
                .nombre(usuarioGuardado.getNombre())
                .apellidos(usuarioGuardado.getApellidos())
                .email(usuarioGuardado.getEmail())
                .curso(usuarioGuardado.getCurso())
                .rol(usuarioGuardado.getRol().name())
                .build();
    }
}