package vdg.marcha.puertollano.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistroActivoResponse {

    private boolean activo;

    private String fechaHoraEntrada;

    private Double kilometros;

}