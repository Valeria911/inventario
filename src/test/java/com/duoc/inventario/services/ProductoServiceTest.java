package com.duoc.inventario.services;

import com.duoc.inventario.entities.Producto;
import com.duoc.inventario.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
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

        when(productoRepository.save(any())).thenReturn(producto);

        //Act
        Producto resultado = productoService.createProducto(producto);

        //Assert
        assertEquals("Leche caliente", resultado.getNombre());
    }
}
