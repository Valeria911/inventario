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
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());
        return CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
    }

    //ver un usuario por id
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-los-usuarios"));
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    //crear un usuario
    @PostMapping
    public EntityModel<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return EntityModel.of(nuevoUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(nuevoUsuario.getIdUsuario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-los-usuarios"));
    }

    //editar un usuario
    @PutMapping("/{id}")
    public EntityModel<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
        return EntityModel.of(usuarioActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuarioActualizado.getIdUsuario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-los-usuarios"));
    }

    //eliminar un usuario
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
