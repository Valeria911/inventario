package com.duoc.inventario.services;

import com.duoc.inventario.entities.Producto;
import com.duoc.inventario.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return productoRepository.findById(id);
    }

    //crear un producto
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    //editar un producto
    public Producto updateProducto(Long id, Producto productoDetails) {
        return productoRepository.findById(id).map(producto1 -> {
            producto1.setNombre(productoDetails.getNombre());
            producto1.setDescripcion(productoDetails.getDescripcion());
            producto1.setPrecioUnitario(productoDetails.getPrecioUnitario());
            producto1.setStockDisponible(productoDetails.getStockDisponible());
            producto1.setIdProveedor(productoDetails.getIdProveedor());
            return ResponseEntity.ok(productoRepository.save(producto1));
        }).orElse(ResponseEntity.notFound().build()).getBody();
    }

    //eliminar un producto
    public ResponseEntity<Void> deleteProducto(Long id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
