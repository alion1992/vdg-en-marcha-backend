package vdg.marcha.puertollano.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import vdg.marcha.puertollano.model.Registro;

import java.util.List;
import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findByUsuarioId(Long usuarioId);

    List<Registro> findByUsuarioIdOrderByFechaDesc(Long usuarioId);

    Optional<Registro> findFirstByUsuarioIdAndHoraSalidaIsNull(
            Long usuarioId);

}
