package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vdg.marcha.puertollano.dto.EntradaRequest;
import vdg.marcha.puertollano.dto.RegistroResponse;
import vdg.marcha.puertollano.model.Registro;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.repository.RegistroRepository;
import vdg.marcha.puertollano.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final RegistroRepository registroRepository;

    private final UsuarioRepository usuarioRepository;

    public List<RegistroResponse> obtenerHistorial(Long usuarioId) {

        return registroRepository
                .findByUsuarioIdOrderByFechaDesc(usuarioId)
                .stream()
                .map(registro -> RegistroResponse.builder()
                        .id(registro.getId())
                        .fecha(registro.getFecha())
                        .horaEntrada(registro.getHoraEntrada())
                        .horaSalida(registro.getHoraSalida())
                        .kilometros(registro.getKilometros())
                        .build())
                .toList();
    }

    public RegistroResponse registrarEntrada(
            EntradaRequest request) {


        if (registroRepository
                .findFirstByUsuarioIdAndHoraSalidaIsNull(
                        request.getUsuarioId())
                .isPresent()) {

            throw new RuntimeException(
                    "Ya existe un trayecto abierto");
        }

        Usuario usuario = usuarioRepository
                .findById(request.getUsuarioId())
                .orElseThrow();

        Registro registro = Registro.builder()
                .fecha(LocalDate.now())
                .horaEntrada(LocalTime.now())
                .kilometros(request.getKilometros())
                .usuario(usuario)
                .build();

        Registro guardado = registroRepository.save(registro);

        return RegistroResponse.builder()
                .id(guardado.getId())
                .fecha(guardado.getFecha())
                .horaEntrada(guardado.getHoraEntrada())
                .horaSalida(guardado.getHoraSalida())
                .kilometros(guardado.getKilometros())
                .usuarioId(guardado.getUsuario().getId())
                .build();
    }

    public RegistroResponse registrarSalida(Long usuarioId) {


        Registro registro = registroRepository
                .findFirstByUsuarioIdAndHoraSalidaIsNull(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe entrada abierta"));

        registro.setHoraSalida(LocalTime.now());

        Registro guardado = registroRepository.save(registro);

        return RegistroResponse.builder()
                .id(guardado.getId())
                .fecha(guardado.getFecha())
                .horaEntrada(guardado.getHoraEntrada())
                .horaSalida(guardado.getHoraSalida())
                .kilometros(guardado.getKilometros())
                .usuarioId(guardado.getUsuario().getId())
                .build();


    }


}
