package vdg.marcha.puertollano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdg.marcha.puertollano.model.HistoricoPuerta;
import vdg.marcha.puertollano.model.Registro;

public interface HistorialPuertaRepository extends JpaRepository<HistoricoPuerta, Long> {


}
