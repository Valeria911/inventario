package com.duoc.inventario.services;

import com.duoc.inventario.entities.Proveedor;
import com.duoc.inventario.repositories.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {

    @InjectMocks
    private ProveedorService proveedorService;

    @Mock
    private ProveedorRepository proveedorRepository;

    @Test
    public void guardarProveedorTest(){
        //Arrange
        Proveedor proveedor = new Proveedor();
        proveedor.setDireccion("Av. Siempre Viva 123");
        proveedor.setNombre("Proveedor1");
        proveedor.setTelefono("123456789");
        proveedor.setEmail("prov@gmail.com");

        when(proveedorRepository.save(any())).thenReturn(proveedor);

        //Act
        Proveedor resultado = proveedorService.createProveedor(proveedor);

        //Assert
        assertEquals("Proveedor1", resultado.getNombre());

    }
}
