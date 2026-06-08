package vdg.marcha.puertollano.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class RegistroResponse {

    private Long id;

    private LocalDate fecha;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private Double kilometros;
}