package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vdg.marcha.puertollano.dto.RegistroResponse;
import vdg.marcha.puertollano.repository.RegistroRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final RegistroRepository registroRepository;

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
}
