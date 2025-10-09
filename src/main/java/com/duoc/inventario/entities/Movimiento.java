package com.duoc.inventario.entities;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Entity
@Table(name= "movimiento")
public class Movimiento extends RepresentationModel<Movimiento> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    @Column
    private Long idProducto;
    private String tipo; // "ingreso" o "egreso"
    private Integer cantidad;

    @Column(name = "fecha_movimiento")
    private String fechaMovimiento;
    private Long idUsuario;

    public Movimiento() {
    }

    public Movimiento(Long idProducto, String tipo, Integer cantidad, String fechaMovimiento, Long idUsuario) {
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.idUsuario = idUsuario;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
