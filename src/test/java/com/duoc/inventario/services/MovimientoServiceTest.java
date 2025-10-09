package com.duoc.inventario.services;

import com.duoc.inventario.entities.Movimiento;
import com.duoc.inventario.repositories.MovimientoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovimientoServiceTest {

    @InjectMocks
    private MovimientoService movimientoService;

    @Mock
    private MovimientoRepository movimientoRepository;

    @Test
    public void guardarMovimientoTest(){
        //Arrange
        Movimiento movimiento = new Movimiento();
        movimiento.setIdProducto(1L);
        movimiento.setFechaMovimiento("2023-10-10");
        movimiento.setCantidad(10);
        movimiento.setTipo("INGRESO");
        movimiento.setIdUsuario(1L);

        when(movimientoRepository.save(any())).thenReturn(movimiento);

        //Act
        Movimiento resultado = movimientoService.createMovimiento(movimiento);

        //Assert
        assertEquals(1L, resultado.getIdProducto());
    }
}
