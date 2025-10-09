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
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductos()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductos());
        return CollectionModel.of(productoResources, linkTo.withRel("productos"));
    }

    //ver un producto por id
    @GetMapping("/{id}")
    public EntityModel<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            return EntityModel.of(producto.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductos()).withRel("todos-los-productos"));
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    //crear un producto
    @PostMapping
    public EntityModel<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return EntityModel.of(nuevoProducto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(nuevoProducto.getIdProducto())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductos()).withRel("todos-los-productos"));
    }

    //editar un producto
    @PutMapping("/{id}")
    public EntityModel<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.updateProducto(id, producto);
        return EntityModel.of(productoActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductoById(productoActualizado.getIdProducto())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProductos()).withRel("todos-los-productos"));
    }

    //eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

}
