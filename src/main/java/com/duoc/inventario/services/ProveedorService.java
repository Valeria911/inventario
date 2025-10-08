package com.duoc.inventario.services;

import com.duoc.inventario.entities.Proveedor;
import com.duoc.inventario.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return proveedorRepository.findById(id).orElse(null);
    }

    //crear un proveedor
    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    //editar un proveedor
    public Proveedor updateProveedor(Long id, Proveedor proveedorDetails) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedor.setNombre(proveedorDetails.getNombre());
            proveedor.setDireccion(proveedorDetails.getDireccion());
            proveedor.setTelefono(proveedorDetails.getTelefono());
            return proveedorRepository.save(proveedor);
        }
        return null;
    }

    //eliminar un proveedor
    public boolean deleteProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedorRepository.delete(proveedor);
            return true;
        }
        return false;
    }
}
