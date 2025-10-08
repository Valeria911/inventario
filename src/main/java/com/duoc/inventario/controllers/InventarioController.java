package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Inventario;
import com.duoc.inventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    //ver todos los inventarios
    @GetMapping
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<Inventario> inventarios = inventarioService.getAllInventarios();

        List<EntityModel<Inventario>> inventarioResources = inventarios.stream()
                .map(inventario -> EntityModel.of(inventario,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getAllInventarios()).withSelfRel()
                ))
                .collect(Collectors.toList());
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getAllInventarios());
            return CollectionModel.of(inventarioResources, linkTo.withRel("inventarios"));
    }

    //ver un inventario por id
    @GetMapping("/{id}")
    public EntityModel<Inventario> getInventarioById(@PathVariable Long idInventario){
        Optional<Inventario> inventario = inventarioService.getInventarioById(idInventario);
        if (inventario.isPresent()) {
            return EntityModel.of(inventario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getInventarioById(idInventario)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getAllInventarios()).withRel("todos-los-inventarios"));
        } else {
            throw new RuntimeException("Inventario no encontrado con id: " + idInventario);
        }
    }

    //crear un inventario
    @PostMapping
    public EntityModel<Inventario> createInventario(@RequestBody Inventario inventario){
        Inventario nuevoInventario = inventarioService.createInventario(inventario);
        return EntityModel.of(nuevoInventario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getInventarioById(nuevoInventario.getIdInventario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getAllInventarios()).withRel("todos-los-inventarios"));
    }

    //editar un inventario
    @PutMapping("/{id}")
    public EntityModel<Inventario> updateInventario(@PathVariable Long idInventario, @RequestBody Inventario inventario){
        Inventario inventarioActualizado = inventarioService.updateInventario(idInventario, inventario);
        return EntityModel.of(inventarioActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getInventarioById(inventarioActualizado.getIdInventario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).getAllInventarios()).withRel("todos-los-inventarios"));
    }

    //eliminar un inventario
    @DeleteMapping("/{id}")
    public void deleteInventario(@PathVariable Long idInventario) {
        inventarioService.deleteInventario(idInventario);
    }
}
