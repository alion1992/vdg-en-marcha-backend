package vdg.marcha.puertollano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdg.marcha.puertollano.model.UsuarioAutorizado;

import java.util.Optional;

public interface UsuarioAutorizadoRepository
        extends JpaRepository<UsuarioAutorizado, Long> {

    Optional<UsuarioAutorizado> findByDni(String dni);

}