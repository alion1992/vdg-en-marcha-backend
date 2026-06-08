package vdg.marcha.puertollano.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vdg.marcha.puertollano.dto.RegistroResponse;
import vdg.marcha.puertollano.service.RegistroService;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

    private final RegistroService registroService;

    @GetMapping("/usuario/{usuarioId}")
    public List<RegistroResponse> historial(
            @PathVariable Long usuarioId) {

        return registroService.obtenerHistorial(usuarioId);
    }

}