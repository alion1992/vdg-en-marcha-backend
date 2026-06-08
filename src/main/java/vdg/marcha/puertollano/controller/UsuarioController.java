package vdg.marcha.puertollano.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vdg.marcha.puertollano.dto.RegistroRequest;
import vdg.marcha.puertollano.dto.UsuarioResponse;
import vdg.marcha.puertollano.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public UsuarioResponse register(
            @Valid @RequestBody RegistroRequest request) {

        return usuarioService.registrar(request);
    }

}