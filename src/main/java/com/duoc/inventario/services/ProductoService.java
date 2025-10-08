package com.duoc.inventario.services;

import com.duoc.inventario.entities.Producto;
import com.duoc.inventario.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //ver todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    //ver un producto por id
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    //crear un producto
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    //editar un producto
    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setNombre(productoDetails.getNombre());
            producto.setDescripcion(productoDetails.getDescripcion());
            producto.setPrecioUnitario(productoDetails.getPrecioUnitario());
            producto.setStockDisponible(productoDetails.getStockDisponible());
            return productoRepository.save(producto);
        }
        return null;
    }

    //eliminar un producto
    public boolean deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            productoRepository.delete(producto);
            return true;
        }
        return false;
    }


}
