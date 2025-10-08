package com.duoc.inventario.services;

import com.duoc.inventario.entities.Inventario;
import com.duoc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    //ver todos los inventarios
    public List<Inventario> getAllInventarios() {
        return inventarioRepository.findAll();
    }

    //ver un inventario por id
    public Optional<Inventario> getInventarioById(Long idInventario) {
        return inventarioRepository.findById(idInventario);
    }

    //crear un inventario
    public Inventario createInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    //editar un inventario
    public Inventario updateInventario(Long idInventario, Inventario inventarioDetails) {
        return inventarioRepository.findById(idInventario).map(inventario -> {
            inventario.setIdProducto(inventarioDetails.getIdProducto());
            inventario.setCantidad(inventarioDetails.getCantidad());
            inventario.setUbicacion(inventarioDetails.getUbicacion());
            inventario.setFechaActualizacion(inventarioDetails.getFechaActualizacion());
            return ResponseEntity.ok(inventarioRepository.save(inventario));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar un inventario
    public ResponseEntity<Void> deleteInventario(Long idInventario) {
        if (inventarioRepository.existsById(idInventario)) {
            inventarioRepository.deleteById(idInventario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
