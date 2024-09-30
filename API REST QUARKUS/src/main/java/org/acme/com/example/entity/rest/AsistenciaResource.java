package org.acme.com.example.entity.rest;

import com.example.entity.Asistencia;
import com.example.repository.AsistenciaRepository;
import com.example.repository.EmpleadoRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/asistencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsistenciaResource {

    @Inject
    AsistenciaRepository asistenciaRepository;

    @Inject
    EmpleadoRepository empleadoRepository;

    // Obtener todas las asistencias
    @GET
    public List<Asistencia> obtenerAsistencias() {
        return asistenciaRepository.listAll();
    }

    // Registrar una nueva asistencia
    @POST
    public Response registrarAsistencia(Asistencia asistencia) {
        if (empleadoRepository.findById(asistencia.getEmpleado().getId()) == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Empleado no existe").build();
        }
        asistenciaRepository.persist(asistencia);
        return Response.status(Response.Status.CREATED).entity(asistencia).build();
    }

    // Obtener una asistencia por su ID
    @GET
    @Path("/{id}")
    public Response obtenerAsistenciaPorId(@PathParam("id") Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id);
        if (asistencia == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(asistencia).build();
    }

    // Actualizar una asistencia existente
    @PUT
    @Path("/{id}")
    public Response actualizarAsistencia(@PathParam("id") Long id, Asistencia asistencia) {
        Asistencia asistenciaExistente = asistenciaRepository.findById(id);
        if (asistenciaExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        asistenciaExistente.setFecha(asistencia.getFecha());
        asistenciaExistente.setHoraEntrada(asistencia.getHoraEntrada());
        asistenciaExistente.setHoraSalida(asistencia.getHoraSalida());
        asistenciaRepository.persist(asistenciaExistente);
        return Response.ok(asistenciaExistente).build();
    }

    // Eliminar una asistencia por su ID
    @DELETE
    @Path("/{id}")
    public Response eliminarAsistencia(@PathParam("id") Long id) {
        boolean eliminado = asistenciaRepository.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
