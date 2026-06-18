package vdg.marcha.puertollano.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vdg.marcha.puertollano.config.ShellyProperties;
import vdg.marcha.puertollano.model.HistoricoPuerta;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.repository.HistorialPuertaRepository;
import vdg.marcha.puertollano.repository.UsuarioRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShellyService {

    private final RestTemplate restTemplate;

    private final ShellyProperties shellyProperties;

    private final UsuarioRepository usuarioRepository;

    private final HistorialPuertaRepository historialPuertaRepository;

    public void abrirPuerta(Authentication authentication) {

        String documento =
                authentication.getName();

        String onUrl =
                "http://" +
                        shellyProperties.getIp() +
                        "/rpc/Switch.Set?id=0&on=true";

        String offUrl =
                "http://" +
                        shellyProperties.getIp() +
                        "/rpc/Switch.Set?id=0&on=false";

        restTemplate.getForObject(
                onUrl,
                String.class
        );

        try {
            Thread.sleep(
                    shellyProperties.getPulseDuration()
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        restTemplate.getForObject(
                offUrl,
                String.class
        );

        Usuario usuario =
                usuarioRepository
                        .findByDni(documento)
                        .orElseThrow();

        HistoricoPuerta puerta = new HistoricoPuerta();
        puerta.setUsuario(usuario);
        puerta.setFechaHora(LocalDateTime.now());
        historialPuertaRepository.save(puerta);
    }

}