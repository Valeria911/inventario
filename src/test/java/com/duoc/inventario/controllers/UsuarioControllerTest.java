package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Usuario;
import com.duoc.inventario.services.UsuarioService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    public void getAllUsuariosTest() throws Exception {
        //Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Usuario 1");
        usuario1.setApellido("Apellido 1");
        usuario1.setRol("Rol 1");
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Usuario 2");
        usuario2.setApellido("Apellido 2");
        usuario2.setRol("Rol 2");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].nombre", Matchers.is("Usuario 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].nombre", Matchers.is("Usuario 2")));

    }

}
