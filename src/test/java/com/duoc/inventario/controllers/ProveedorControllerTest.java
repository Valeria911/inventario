package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Proveedor;
import com.duoc.inventario.services.ProductoService;
import com.duoc.inventario.services.ProveedorService;
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

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProveedorService proveedorService;

    @Test
    public void getAllProveedoresTest() throws Exception {
        //Arrange
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombre("Proveedor 1");
        proveedor1.setDireccion("Direccion 1");
        proveedor1.setTelefono("123456789");
        proveedor1.setEmail("prov1@gmail.com");
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setNombre("Proveedor 2");
        proveedor2.setDireccion("Direccion 2");
        proveedor2.setTelefono("987654321");
        proveedor2.setEmail("prov2@gmail.com");

        List<Proveedor> proveedores = Arrays.asList(proveedor1, proveedor2);
        when(proveedorService.getAllProveedores()).thenReturn(proveedores);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/proveedores"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.proveedorList[0].nombre", Matchers.is("Proveedor 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.proveedorList[1].nombre", Matchers.is("Proveedor 2")));
    }
}
