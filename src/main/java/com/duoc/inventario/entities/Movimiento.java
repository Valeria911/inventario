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
    private Producto idProducto;
    private String tipo; // "ingreso" o "egreso"
    private Integer cantidad;

    @Column(name = "fecha_movimiento")
    private LocalDate fechaMovimiento;
    private Usuario idUsuario;

    public Movimiento() {
    }

    public Movimiento(Producto idProducto, String tipo, Integer cantidad, LocalDate fechaMovimiento, Usuario idUsuario) {
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

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
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

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
