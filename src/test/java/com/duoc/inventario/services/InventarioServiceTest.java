package com.duoc.inventario.services;

import com.duoc.inventario.entities.Inventario;
import com.duoc.inventario.repositories.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @InjectMocks
    private InventarioService inventarioService;

    @Mock
    private InventarioRepository inventarioRepository;

    @Test
    public void guardarInventarioTest(){
        //Arrange
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1L);
        inventario.setCantidad(100);
        inventario.setUbicacion("Bodega Central");
        inventario.setFechaActualizacion("2023-10-10");

        when(inventarioRepository.save(any())).thenReturn(inventario);

        //Act
        Inventario resultado = inventarioService.createInventario(inventario);

        //Assert
        assert resultado.getIdProducto().equals(1L);

    }
}
