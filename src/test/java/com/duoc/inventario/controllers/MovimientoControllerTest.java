package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Movimiento;
import com.duoc.inventario.services.MovimientoService;
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

@WebMvcTest(MovimientoController.class)
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovimientoService movimientoService;

    @Test
    public void getAllMovimientosTest() throws Exception {
        //Arrange
        Movimiento movimiento1 = new Movimiento();
        movimiento1.setTipo("Ingreso");
        movimiento1.setCantidad(50);
        movimiento1.setIdProducto(1L);
        movimiento1.setIdUsuario(1L);
        movimiento1.setFechaMovimiento("2023-10-10");
        Movimiento movimiento2 = new Movimiento();
        movimiento2.setTipo("Egreso");
        movimiento2.setCantidad(20);
        movimiento2.setIdProducto(2L);
        movimiento2.setIdUsuario(2L);
        movimiento2.setFechaMovimiento("2023-10-11");

        List<Movimiento> movimientos = Arrays.asList(movimiento1, movimiento2);
        when(movimientoService.getAllMovimientos()).thenReturn(movimientos);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/movimientos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.movimientoList[0].tipo", Matchers.is("Ingreso")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.movimientoList[1].tipo", Matchers.is("Egreso")));
    }
}
