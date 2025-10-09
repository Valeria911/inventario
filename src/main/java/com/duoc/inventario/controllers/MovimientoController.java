package com.duoc.inventario.controllers;

import com.duoc.inventario.entities.Movimiento;
import com.duoc.inventario.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    //ver todos los movimientos
    @GetMapping
    public CollectionModel<EntityModel<Movimiento>> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoService.getAllMovimientos();

        List<EntityModel<Movimiento>> movimientoResources = movimientos.stream()
                .map(movimiento -> EntityModel.of(movimiento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovimientos()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovimientos());
        return CollectionModel.of(movimientoResources, linkTo.withRel("movimientos"));
    }

    //ver un movimiento por id
    @GetMapping("/{id}")
    public EntityModel<Movimiento> getMovimientoById(@PathVariable Long id){
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(id);
        if (movimiento.isPresent()) {
            return EntityModel.of(movimiento.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovimientoById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovimientos()).withRel("todos-los-movimientos"));
        } else {
            throw new RuntimeException("Movimiento no encontrado con id: " + id);
        }
    }

    //crear un movimiento
    @PostMapping
    public EntityModel<Movimiento> createMovimiento(@RequestBody Movimiento movimiento){
        Movimiento nuevoMovimiento = movimientoService.createMovimiento(movimiento);
        return EntityModel.of(nuevoMovimiento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovimientoById(nuevoMovimiento.getIdMovimiento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovimientos()).withRel("todos-los-movimientos"));
    }

    //editar un movimiento
    @PutMapping("/{id}")
    public EntityModel<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento){
        Movimiento movimientoActualizado = movimientoService.updateMovimiento(id, movimiento);
        return EntityModel.of(movimientoActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovimientoById(movimientoActualizado.getIdMovimiento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovimientos()).withRel("todos-los-movimientos"));
    }

    //eliminar un movimiento
    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id){
        movimientoService.deleteMovimiento(id);
    }
}
