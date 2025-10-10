package com.duoc.inventario.repositories;

import com.duoc.inventario.entities.Inventario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventarioRepositoryTest {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Test
    public void guardarInventarioTest(){
        //Arrange
        Inventario inventario = new Inventario();
        inventario.setFechaActualizacion("2023-10-10");
        inventario.setIdProducto(1L);
        inventario.setCantidad(100);
        inventario.setUbicacion("Bodega Central");

        //Act
        Inventario resultado = inventarioRepository.save(inventario);

        //Assert
        assertNotNull(resultado.getIdInventario());
        assertEquals(1L, resultado.getIdProducto());
    }

    @Test
    public void buscarInventarioPorIdTest(){
        //Arrange
        Inventario guardar = new Inventario();
        guardar.setFechaActualizacion("2023-10-10");
        guardar.setIdProducto(1L);
        guardar.setCantidad(100);
        guardar.setUbicacion("Bodega Central");
        Inventario inventarioGuardado = inventarioRepository.save(guardar);

        //Act
        Inventario resultado = inventarioRepository.findById(inventarioGuardado.getIdInventario()).orElse(null);

        //Assert
        assertNotNull(resultado);
        assertEquals(inventarioGuardado.getIdInventario(), resultado.getIdInventario());
    }
}
