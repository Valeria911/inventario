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

    @Test
    public void actualizarMovimientoTest(){
        //Arrange
        Long idMovimiento = 1L;
        Movimiento movimientoExistente = new Movimiento();
        movimientoExistente.setIdMovimiento(idMovimiento);
        movimientoExistente.setIdProducto(1L);
        movimientoExistente.setFechaMovimiento("2023-10-10");
        movimientoExistente.setCantidad(10);
        movimientoExistente.setTipo("INGRESO");
        movimientoExistente.setIdUsuario(1L);

        Movimiento movimientoActualizado = new Movimiento();
        movimientoActualizado.setIdMovimiento(idMovimiento);
        movimientoActualizado.setIdProducto(2L);
        movimientoActualizado.setFechaMovimiento("2023-10-11");
        movimientoActualizado.setCantidad(20);
        movimientoActualizado.setTipo("EGRESO");
        movimientoActualizado.setIdUsuario(2L);

        when(movimientoRepository.findById(idMovimiento)).thenReturn(java.util.Optional.of(movimientoExistente));
        when(movimientoRepository.save(any())).thenReturn(movimientoActualizado);

        //Act
        Movimiento resultado = movimientoService.updateMovimiento(idMovimiento, movimientoActualizado);

        //Assert
        assertEquals(2L, resultado.getIdProducto());
    }
}
