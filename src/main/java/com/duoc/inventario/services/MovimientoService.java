package com.duoc.inventario.services;

import com.duoc.inventario.entities.Movimiento;
import com.duoc.inventario.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return movimientoRepository.findById(id).orElse(null);
    }

    //crear un movimiento
    public Movimiento createMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    //editar un movimiento
    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if (movimiento != null) {
            movimiento.setTipo(movimientoDetails.getTipo());
            movimiento.setCantidad(movimientoDetails.getCantidad());
            movimiento.setFechaMovimiento(movimientoDetails.getFechaMovimiento());
            movimiento.setIdProducto(movimientoDetails.getIdProducto());
            return movimientoRepository.save(movimiento);
        }
        return null;
    }

    //eliminar un movimiento
    public boolean deleteMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if (movimiento != null) {
            movimientoRepository.delete(movimiento);
            return true;
        }
        return false;
    }
}
