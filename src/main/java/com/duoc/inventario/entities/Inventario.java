package com.duoc.inventario.entities;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Entity
@Table(name= "inventario")
public class Inventario extends RepresentationModel<Inventario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @Column
    private Producto idProducto;
    private Integer cantidad;
    private String ubicacion; // bodega o estantería

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    public Inventario() {
    }

    public Inventario(Producto idProducto, Integer cantidad, String ubicacion, LocalDate fechaActualizacion) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
