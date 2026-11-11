package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vdg.marcha.puertollano.dto.LoginRequest;
import vdg.marcha.puertollano.dto.LoginResponse;
import vdg.marcha.puertollano.dto.RegistroRequest;
import vdg.marcha.puertollano.dto.UsuarioResponse;
import vdg.marcha.puertollano.model.Rol;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.model.UsuarioAutorizado;
import vdg.marcha.puertollano.repository.UsuarioAutorizadoRepository;
import vdg.marcha.puertollano.repository.UsuarioRepository;
import vdg.marcha.puertollano.security.JwtService;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioAutorizadoRepository usuarioAutorizadoRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UsuarioResponse registrar(RegistroRequest request) {

        if (usuarioRepository.findByDni(request.getUsuario()).isPresent()) {
            throw new RuntimeException("Este usuario ya tiene una cuenta activa");
        }

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        UsuarioAutorizado autorizado =
                usuarioAutorizadoRepository
                        .findByDni(request.getUsuario())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usuario no autorizado"));

        if (autorizado.getTieneCuenta()) {
            throw new RuntimeException(
                    "Este usuario ya tiene una cuenta vinculada");
        }

        Usuario usuario = Usuario.builder()
                .dni(request.getUsuario())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.ALUMNO)
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        //Actualizamos la tabla de autorizados para indicar que ya tiene cuenta
        autorizado.setTieneCuenta(true);
        usuarioAutorizadoRepository.save(autorizado);

        return UsuarioResponse.builder()
                .id(usuarioGuardado.getId())
                .usuario(usuarioGuardado.getDni())
                .nombre(usuarioGuardado.getNombre())
                .apellidos(usuarioGuardado.getApellidos())
                .email(usuarioGuardado.getEmail())
                .rol(usuarioGuardado.getRol().name())
                .build();
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository
                .findByDni(request.getUsuario())
                .orElseThrow(() ->
                        new RuntimeException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {

            throw new RuntimeException(
                    "Usuario o contraseña incorrectos");
        }

        UsuarioAutorizado autorizado =
                usuarioAutorizadoRepository
                        .findByDni(request.getUsuario())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usuario no autorizado"));

        if (!autorizado.getActiva()) {
            throw new RuntimeException(
                    "La cuenta esta desactivada, contacta con jefatura de estudios para su activación");
        }


        return LoginResponse.builder()
                .id(usuario.getId())
                .usuario(usuario.getDni())
                .nombre(usuario.getNombre())
                .token(jwtService.generateToken(
                        usuario.getDni()))
                .build();
    }
}