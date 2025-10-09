package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Producto;
import com.duoc.inventario.services.ProductoService;
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

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Test
    public void getAllProductosTest() throws Exception {
        //Arrange
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setDescripcion("Descripcion 1");
        producto1.setPrecioUnitario(1000.0);
        producto1.setStockDisponible(10);
        producto1.setIdProveedor(1L);
        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");
        producto2.setDescripcion("Descripcion 2");
        producto2.setPrecioUnitario(2000.0);
        producto2.setStockDisponible(20);
        producto2.setIdProveedor(2L);

        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(productoService.getAllProductos()).thenReturn(productos);

        //Act y Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/productos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productoList[0].nombre", Matchers.is("Producto 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.productoList[1].nombre", Matchers.is("Producto 2")));
    }
}
