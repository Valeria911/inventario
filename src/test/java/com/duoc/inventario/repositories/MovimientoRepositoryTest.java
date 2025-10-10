package com.duoc.inventario.repositories;

import com.duoc.inventario.entities.Movimiento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovimientoRepositoryTest {

    @Autowired
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

        //Act
        Movimiento resultado = movimientoRepository.save(movimiento);

        //Assert
        assertNotNull(resultado.getIdMovimiento());
        assertEquals(1L, resultado.getIdProducto());
    }

    @Test
    public void eliminarMovimientoTest(){
        //Arrange
        Movimiento movimiento = new Movimiento();
        movimiento.setIdProducto(1L);
        movimiento.setFechaMovimiento("2023-10-10");
        movimiento.setCantidad(10);
        movimiento.setTipo("INGRESO");
        movimiento.setIdUsuario(1L);
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);

        //Act
        movimientoRepository.deleteById(movimientoGuardado.getIdMovimiento());
        Movimiento resultado = movimientoRepository.findById(movimientoGuardado.getIdMovimiento()).orElse(null);

        //Assert
        assertEquals(null, resultado);
    }
}
