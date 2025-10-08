package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Usuario;
import com.duoc.inventario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //ver todos los usuarios
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        List<EntityModel<Usuario>> usuarioResources = usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios());
        return CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
    }

    //ver un usuario por id
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(idUsuario)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("todos-los-usuarios"));
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }
    }

    //crear un usuario
    @PostMapping
    public EntityModel<Usuario> createUsuario(Usuario usuario){
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return EntityModel.of(nuevoUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(nuevoUsuario.getIdUsuario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("todos-los-usuarios"));
    }

    //editar un usuario
    @PutMapping("/{id}")
    public EntityModel<Usuario> updateUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
        Usuario usuarioActualizado = usuarioService.updateUsuario(idUsuario, usuario);
        return EntityModel.of(usuarioActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getUsuarioById(usuarioActualizado.getIdUsuario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).getAllUsuarios()).withRel("todos-los-usuarios"));
    }

    //eliminar un usuario
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long idUsuario) {
        usuarioService.deleteUsuario(idUsuario);
    }
}
