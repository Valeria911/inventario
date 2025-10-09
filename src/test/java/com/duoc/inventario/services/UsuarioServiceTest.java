package com.duoc.inventario.services;

import com.duoc.inventario.entities.Usuario;
import com.duoc.inventario.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest(){
        //Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setRol("Administrativo");

        when(usuarioRepository.save(any())).thenReturn(usuario);

        //Act
        Usuario resultado = usuarioService.createUsuario(usuario);

        //Assert
        assertEquals("Juan", resultado.getNombre());
    }
}
