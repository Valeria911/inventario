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
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getAllMovimientos()).withSelfRel()
                ))
                .collect(Collectors.toList());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getAllMovimientos());
        return CollectionModel.of(movimientoResources, linkTo.withRel("movimientos"));
    }

    //ver un movimiento por id
    @GetMapping("/{id}")
    public EntityModel<Movimiento> getMovimientoById(@PathVariable Long idMovimiento){
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(idMovimiento);
        if (movimiento.isPresent()) {
            return EntityModel.of(movimiento.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getMovimientoById(idMovimiento)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getAllMovimientos()).withRel("todos-los-movimientos"));
        } else {
            throw new RuntimeException("Movimiento no encontrado con id: " + idMovimiento);
        }
    }

    //crear un movimiento
    @PostMapping
    public EntityModel<Movimiento> createMovimiento(Movimiento movimiento){
        Movimiento nuevoMovimiento = movimientoService.createMovimiento(movimiento);
        return EntityModel.of(nuevoMovimiento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getMovimientoById(nuevoMovimiento.getIdMovimiento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getAllMovimientos()).withRel("todos-los-movimientos"));
    }

    //editar un movimiento
    @PutMapping("/{id}")
    public EntityModel<Movimiento> updateMovimiento(@PathVariable Long idMovimiento, @RequestBody Movimiento movimiento){
        Movimiento movimientoActualizado = movimientoService.updateMovimiento(idMovimiento, movimiento);
        return EntityModel.of(movimientoActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getMovimientoById(movimientoActualizado.getIdMovimiento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovimientoController.class).getAllMovimientos()).withRel("todos-los-movimientos"));
    }

    //eliminar un movimiento
    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long idMovimiento){
        movimientoService.deleteMovimiento(idMovimiento);
    }
}
