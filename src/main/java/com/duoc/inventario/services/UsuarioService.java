package com.duoc.inventario.services;

import com.duoc.inventario.entities.Usuario;
import com.duoc.inventario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //ver todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    //ver un usuario por id
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    //crear un usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //editar un usuario
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setRol(usuarioDetails.getRol());
            usuario.setEstado(usuarioDetails.getEstado());
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar un usuario
    public ResponseEntity<Void> deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}