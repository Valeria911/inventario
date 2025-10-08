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
    public Optional<Usuario> getUsuarioById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    //crear un usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //editar un usuario
    public Usuario updateUsuario(Long idUsuario, Usuario usuarioDetails) {
        return usuarioRepository.findById(idUsuario).map(usuario -> {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setRol(usuarioDetails.getRol());
            usuario.setEstado(usuarioDetails.getEstado());
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar un usuario
    public ResponseEntity<Void> deleteUsuario(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}