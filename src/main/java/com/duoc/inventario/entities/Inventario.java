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
    private Long idProducto;
    private Integer cantidad;
    private String ubicacion; // bodega o estantería

    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public Inventario() {
    }

    public Inventario(Long idProducto, Integer cantidad, String ubicacion, String fechaActualizacion) {
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

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
