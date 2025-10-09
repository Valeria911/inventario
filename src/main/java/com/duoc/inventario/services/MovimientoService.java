package com.duoc.inventario.services;

import com.duoc.inventario.entities.Movimiento;
import com.duoc.inventario.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    //ver todos los movimientos
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    //ver un movimiento por id
    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    //crear un movimiento
    public Movimiento createMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    //editar un movimiento
    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        return movimientoRepository.findById(id).map(movimiento -> {
            movimiento.setIdProducto(movimientoDetails.getIdProducto());
            movimiento.setFechaMovimiento(movimientoDetails.getFechaMovimiento());
            movimiento.setCantidad(movimientoDetails.getCantidad());
            movimiento.setTipo(movimientoDetails.getTipo());
            movimiento.setIdUsuario(movimientoDetails.getIdUsuario());
            return ResponseEntity.ok(movimientoRepository.save(movimiento));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar un movimiento
    public ResponseEntity<Void> deleteMovimiento(Long id) {
        if (movimientoRepository.existsById(id)) {
            movimientoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
