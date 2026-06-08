package vdg.marcha.puertollano.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    @Column(nullable = false)
    private Double kilometros;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
