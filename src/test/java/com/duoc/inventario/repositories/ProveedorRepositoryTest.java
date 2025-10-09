package com.duoc.inventario.repositories;

import com.duoc.inventario.entities.Proveedor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProveedorRepositoryTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    public void guardarProveedorTest(){
        //Arrange
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor 1");
        proveedor.setDireccion("Direccion 1");
        proveedor.setTelefono("912345678");
        proveedor.setEmail("p1@gmail.com");
        proveedor.setRut("12.345.678-9");

        //Act
        Proveedor resultado = proveedorRepository.save(proveedor);

        //Assert
        assertNotNull(resultado.getIdProveedor());
        assertEquals("Proveedor 1", resultado.getNombre());
    }
}
