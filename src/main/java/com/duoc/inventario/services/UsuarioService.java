package com.duoc.inventario.services;

import com.duoc.inventario.entities.Usuario;
import com.duoc.inventario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    //crear un usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //editar un usuario
    public Usuario updateUsuario(Long idUsuario, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setRol(usuarioDetails.getRol());
            usuario.setEstado(usuarioDetails.getEstado());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    //eliminar un usuario
    public boolean deleteUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
            return true;
        } else {
            return false;
        }
    }

}