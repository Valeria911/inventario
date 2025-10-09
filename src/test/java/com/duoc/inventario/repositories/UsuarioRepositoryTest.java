package com.duoc.inventario.repositories;

import com.duoc.inventario.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarUsuarioTest(){
        //Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setApellido("Santana");
        usuario.setRol("Tecnico");
        usuario.setEstado(true);

        //Act
        Usuario resultado = usuarioRepository.save(usuario);

        //Assert
        assertNotNull(resultado.getIdUsuario());
        assertEquals("Carlos", resultado.getNombre());
    }
}
