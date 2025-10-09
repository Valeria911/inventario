package com.duoc.inventario.repositories;

import com.duoc.inventario.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void guardarProductoTest(){
        //Arrange
        Producto producto = new Producto();
        producto.setNombre("Leche caliente");
        producto.setCategoria("Lacteos");
        producto.setDescripcion("Taza 200 cc");
        producto.setStockDisponible(50);
        producto.setPrecioUnitario(1200.0);
        producto.setIdProveedor(1L);

        //Act
        Producto resultado = productoRepository.save(producto);

        //Assert
        assertNotNull(resultado.getIdProducto());
        assertEquals("Leche caliente", resultado.getNombre());
    }
}
