package vdg.marcha.puertollano.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import vdg.marcha.puertollano.model.Registro;
import vdg.marcha.puertollano.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro, Long> {


    Optional<Registro> findFirstByUsuarioIdAndFechaHoraSalidaIsNull(
            Long usuarioId);

    List<Registro> findByUsuarioOrderByFechaHoraEntradaDesc(
            Usuario usuario);

}
