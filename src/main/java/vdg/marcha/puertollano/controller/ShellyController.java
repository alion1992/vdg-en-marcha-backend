package vdg.marcha.puertollano.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vdg.marcha.puertollano.dto.ShellyResponse;
import vdg.marcha.puertollano.service.ShellyService;

@RestController
@RequestMapping("/api/shelly")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ShellyController {

    private final ShellyService shellyService;

    @PostMapping("/abrir")
    public ShellyResponse abrir() {

        shellyService.abrirPuerta();

        return ShellyResponse.builder()
                .success(true)
                .message("Puerta abierta correctamente")
                .build();
    }
}