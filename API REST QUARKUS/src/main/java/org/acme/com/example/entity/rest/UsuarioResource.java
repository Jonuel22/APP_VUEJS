package org.acme.com.example.entity.rest;

import com.example.entity.Usuario;
import com.example.repository.UsuarioRepository;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    @GET
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.listAll();
    }

    // Crear un nuevo usuario
    @POST
    public Response crearUsuario(Usuario usuario) {
        usuarioRepository.persist(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    // Obtener un usuario por su ID
    @GET
    @Path("/{id}")
    public Response obtenerUsuarioPorId(@PathParam("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    // Actualizar un usuario existente
    @PUT
    @Path("/{id}")
    public Response actualizarUsuario(@PathParam("id") Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setRol(usuario.getRol());
        usuarioRepository.persist(usuarioExistente);
        return Response.ok(usuarioExistente).build();
    }

    // Eliminar un usuario por su ID
    @DELETE
    @Path("/{id}")
    public Response eliminarUsuario(@PathParam("id") Long id) {
        boolean eliminado = usuarioRepository.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
