package com.duoc.inventario.services;

import com.duoc.inventario.entities.Proveedor;
import com.duoc.inventario.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    //ver todos los proveedores
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    //ver un proveedor por id
    public Optional<Proveedor> getProveedorById(Long id) {
        return proveedorRepository.findById(id);
    }

    //crear un proveedor
    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    //editar un proveedor
    public Proveedor updateProveedor(Long id, Proveedor proveedorDetails) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(proveedorDetails.getNombre());
            proveedor.setDireccion(proveedorDetails.getDireccion());
            proveedor.setTelefono(proveedorDetails.getTelefono());
            return ResponseEntity.ok(proveedorRepository.save(proveedor));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }


    //eliminar un proveedor
    public ResponseEntity<Void> deleteProveedor(Long idProveedor){
        if (proveedorRepository.existsById(idProveedor)){
            proveedorRepository.deleteById(idProveedor);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
