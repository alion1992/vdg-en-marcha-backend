package vdg.marcha.puertollano.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "usuario_id",
            nullable = false
    )
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fechaHoraEntrada;

    private LocalDateTime fechaHoraSalida;

    @Column(nullable = false)
    private Double kilometros;

}