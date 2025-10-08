package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Producto;
import com.duoc.inventario.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //ver todos los productos
    @GetMapping
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();

        List<EntityModel<Producto>> productoResources = productos.stream()
                .map(producto -> EntityModel.of(producto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getAllProductos()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getAllProductos());
        return CollectionModel.of(productoResources, linkTo.withRel("productos"));
    }

    //ver un producto por id
    @GetMapping("/{id}")
    public EntityModel<Producto> getProductoById(@PathVariable Long idProducto) {
        Optional<Producto> producto = productoService.getProductoById(idProducto);
        if (producto.isPresent()) {
            return EntityModel.of(producto.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getProductoById(idProducto)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getAllProductos()).withRel("todos-los-productos"));
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + idProducto);
        }
    }

    //crear un producto
    @PostMapping
    public EntityModel<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return EntityModel.of(nuevoProducto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getProductoById(nuevoProducto.getIdProducto())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getAllProductos()).withRel("todos-los-productos"));
    }

    //editar un producto
    @PutMapping("/{id}")
    public EntityModel<Producto> updateProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.updateProducto(idProducto, producto);
        return EntityModel.of(productoActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getProductoById(productoActualizado.getIdProducto())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).getAllProductos()).withRel("todos-los-productos"));
    }

    //eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long idProducto) {
        productoService.deleteProducto(idProducto);
    }

}
