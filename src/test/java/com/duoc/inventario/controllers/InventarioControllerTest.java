package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Inventario;
import com.duoc.inventario.services.InventarioService;
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

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventarioService inventarioService;

    @Test
    public void getAllInventarioTest() throws Exception {
        //Arrange
        Inventario inventario1 = new Inventario();
        inventario1.setCantidad(50);
        inventario1.setIdProducto(1L);
        Inventario inventario2 = new Inventario();
        inventario2.setCantidad(100);
        inventario2.setIdProducto(2L);

        List<Inventario> inventarios = Arrays.asList(inventario1, inventario2);
        when(inventarioService.getAllInventarios()).thenReturn(inventarios);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/inventarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.inventarioList[0].cantidad", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.inventarioList[1].cantidad", Matchers.is(100)));
    }
}
