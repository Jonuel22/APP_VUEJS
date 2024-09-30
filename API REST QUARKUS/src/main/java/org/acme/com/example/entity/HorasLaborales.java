package org.acme.com.example.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class HorasLaborales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private LocalDate fecha;
    private Double horasTrabajadas;

    @Column(name = "registrado_en")
    private LocalDate registradoEn = LocalDate.now();

    // Getters y setters
}
