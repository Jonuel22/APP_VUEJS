package org.acme.com.example.entity.repository;

import org.acme.com.example.entity.Asistencia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AsistenciaRepository implements PanacheRepository<Asistencia> {
    // Métodos personalizados para consultas adicionales pueden agregarse aquí
}
