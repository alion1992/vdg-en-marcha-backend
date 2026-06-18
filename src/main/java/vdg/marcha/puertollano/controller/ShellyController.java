package vdg.marcha.puertollano.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vdg.marcha.puertollano.dto.ShellyResponse;
import vdg.marcha.puertollano.model.Usuario;
import vdg.marcha.puertollano.service.ShellyService;

@RestController
@RequestMapping("/api/shelly")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ShellyController {



    private final ShellyService shellyService;

    @PostMapping("/abrir")
    public ResponseEntity<Void> abrirPuerta(
            Authentication authentication) {

        shellyService.abrirPuerta(authentication);

        return ResponseEntity.ok().build();
    }
}