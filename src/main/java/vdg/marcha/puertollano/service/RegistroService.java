package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vdg.marcha.puertollano.dto.EntradaRequest;
import vdg.marcha.puertollano.dto.RegistroActivoResponse;
import vdg.marcha.puertollano.dto.RegistroResponse;
import vdg.marcha.puertollano.model.Registro;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.repository.RegistroRepository;
import vdg.marcha.puertollano.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final RegistroRepository registroRepository;

    private final UsuarioRepository usuarioRepository;



    public RegistroActivoResponse obtenerRegistroActivo(
            Authentication authentication) {

        String dni =
                authentication.getName();

        Usuario usuario =
                usuarioRepository
                        .findByDni(dni)
                        .orElseThrow();

        return registroRepository
                .findFirstByUsuarioIdAndFechaHoraSalidaIsNull(
                        usuario.getId()
                )
                .map(registro ->
                        RegistroActivoResponse
                                .builder()
                                .activo(true)
                                .fechaHoraEntrada(
                                        registro
                                                .getFechaHoraEntrada()
                                                .toString()
                                )
                                .kilometros(
                                        registro.getKilometros()
                                )
                                .build()
                )
                .orElse(
                        RegistroActivoResponse
                                .builder()
                                .activo(false)
                                .build()
                );
    }

    public RegistroResponse registrarEntrada(
            EntradaRequest request,
            Authentication authentication) {

        String dni =
                authentication.getName();

        Usuario usuario =
                usuarioRepository
                        .findByDni(dni)
                        .orElseThrow();

        Registro registro =
                new Registro();

        registro.setUsuario(usuario);

        registro.setFechaHoraEntrada(
                LocalDateTime.now()
        );

        registro.setKilometros(
                request.getKilometros()
        );

        registroRepository.save(registro);

        return convertirAResponse(registro);
    }

    public void registrarSalida(
            Authentication authentication) {

        String dni =
                authentication.getName();

        Usuario usuario =
                usuarioRepository
                        .findByDni(dni)
                        .orElseThrow();

        Registro registro =
                registroRepository
                        .findFirstByUsuarioIdAndFechaHoraSalidaIsNull(
                                usuario.getId()
                        )
                        .orElseThrow();

        registro.setFechaHoraSalida(
                LocalDateTime.now()
        );

        registroRepository.save(registro);
    }

    private RegistroResponse convertirAResponse(
            Registro registro) {

        return RegistroResponse.builder()

                .id(registro.getId())

                .fechaHoraEntrada(
                        registro.getFechaHoraEntrada()
                                .toString()
                )

                .fechaHoraSalida(
                        registro.getFechaHoraSalida() != null
                                ? registro.getFechaHoraSalida()
                                .toString()
                                : null
                )

                .kilometros(
                        registro.getKilometros()
                )

                .build();
    }

    public List<RegistroResponse> obtenerHistorial(Authentication authentication) {
        String dni =
                authentication.getName();
        Usuario usuario =
                usuarioRepository
                        .findByDni(dni)
                        .orElseThrow();

        return registroRepository
                .findByUsuarioOrderByFechaHoraEntradaDesc(usuario)
                .stream()
                .map(this::convertirAResponse)
                .toList();

    }
}




