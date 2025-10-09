package com.duoc.inventario.entities;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name= "producto_inventario")
public class Producto extends RepresentationModel<Producto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column
    private String nombre;
    private String categoria;
    private String descripcion;

    @Column(name = "stock_disponible")
    private Integer stockDisponible;

    @Column(name = "precio_unitario")
    private Double precioUnitario;
    private Long idProveedor;

    public Producto() {
    }

    public Producto(Long idProducto, String nombre, String categoria, String descripcion, Integer stockDisponible, Double precioUnitario, Long idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.stockDisponible = stockDisponible;
        this.precioUnitario = precioUnitario;
        this.idProveedor = idProveedor;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

}
