package com.mycompany.DAO;

import com.mycompany.MODELO.ENTIDADES.Producto;

import java.time.LocalDateTime;

public class Reserva {
    private Producto producto;
    private int cantidad;
    private LocalDateTime horario;
    private int idUsuario;

    public Reserva(Producto producto, int cantidad, LocalDateTime horario, int idUsuario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.horario = horario;
        this.idUsuario = idUsuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", horario=" + horario +
                ", idUsuario=" + idUsuario +
                '}';
    }

    public int getUsuarioId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}