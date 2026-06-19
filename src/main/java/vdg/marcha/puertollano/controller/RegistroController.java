package vdg.marcha.puertollano.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vdg.marcha.puertollano.dto.EntradaRequest;
import vdg.marcha.puertollano.dto.RegistroActivoResponse;
import vdg.marcha.puertollano.dto.RegistroResponse;
import vdg.marcha.puertollano.service.RegistroService;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;

    @GetMapping("/activo")
    public RegistroActivoResponse obtenerActivo(
            Authentication authentication) {

        return registroService
                .obtenerRegistroActivo(
                        authentication
                );
    }

    @PostMapping("/entrada")
    public RegistroResponse registrarEntrada(
            @RequestBody EntradaRequest request,
            Authentication authentication) {

        return registroService
                .registrarEntrada(
                        request,
                        authentication
                );
    }

    @PostMapping("/salida")
    public void registrarSalida(
            Authentication authentication) {

        registroService
                .registrarSalida(
                        authentication
                );
    }

    @GetMapping("/historial")
    public List<RegistroResponse> obtenerHistorial(Authentication authentication) {

         List<RegistroResponse> lista = registroService.obtenerHistorial(authentication);
        return lista;

    }

}
