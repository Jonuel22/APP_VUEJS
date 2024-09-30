package org.acme.com.example.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String password;
    private String rol;
    
    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

    // Getters y setters
}
