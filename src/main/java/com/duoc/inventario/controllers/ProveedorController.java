package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Proveedor;
import com.duoc.inventario.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    //ver todos los proveedores
    @GetMapping
    public CollectionModel<EntityModel<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.getAllProveedores();

        List<EntityModel<Proveedor>> proveedorResources = proveedores.stream()
                .map(proveedor -> EntityModel.of(proveedor,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getAllProveedores()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getAllProveedores());
        return CollectionModel.of(proveedorResources, linkTo.withRel("proveedores"));
    }

    //ver un proveedor por id
    @GetMapping("/{id}")
    public EntityModel<Proveedor> getProveedorById(@PathVariable Long idProveedor) {
        Optional<Proveedor> proveedor = proveedorService.getProveedorById(idProveedor);
        if (proveedor.isPresent()) {
            return EntityModel.of(proveedor.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getProveedorById(idProveedor)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getAllProveedores()).withRel("todos-los-proveedores"));
        } else {
            throw new RuntimeException("Proveedor no encontrado con id: " + idProveedor);
        }
    }

    //crear un proveedor
    @PostMapping
    public EntityModel<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.createProveedor(proveedor);
        return EntityModel.of(nuevoProveedor,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getProveedorById(nuevoProveedor.getIdProveedor())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getAllProveedores()).withRel("todos-los-proveedores"));
    }

    //editar un proveedor
    @PutMapping("/{id}")
    public EntityModel<Proveedor> updateProveedor(@PathVariable Long idProveedor, @RequestBody Proveedor proveedor) {
        Proveedor proveedorActualizado = proveedorService.updateProveedor(idProveedor, proveedor);
        return EntityModel.of(proveedorActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getProveedorById(proveedorActualizado.getIdProveedor())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProveedorController.class).getAllProveedores()).withRel("todos-los-proveedores"));
    }

    //eliminar un proveedor
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long idProveedor) {
        proveedorService.deleteProveedor(idProveedor);
    }
}
