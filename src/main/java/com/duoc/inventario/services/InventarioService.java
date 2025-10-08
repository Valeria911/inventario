package com.duoc.inventario.services;

import com.duoc.inventario.entities.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioService inventarioService;

    //ver todos los inventarios
    public List<Inventario> getAllInventarios() {
        return inventarioService.getAllInventarios();
    }

    //ver un inventario por id
    public Optional<Inventario> getInventarioById(Long idInventario) {
        return inventarioService.getInventarioById(idInventario);
    }

    //crear un inventario
    public Inventario createInventario(Inventario inventario) {
        return inventarioService.createInventario(inventario);
    }

    //editar un inventario
    public Inventario updateInventario(Long idInventario, Inventario inventarioDetails) {
        return inventarioService.updateInventario(idInventario, inventarioDetails);
    }

    //eliminar un inventario
    public void deleteInventario(Long idInventario) {
        inventarioService.deleteInventario(idInventario);
    }
}
