package com.mycompany.MODELO.ENTIDADES;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String contraseña;
    private boolean esAdmin;

    public Usuario(String nombre, String contraseña, boolean esAdmin) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.esAdmin = esAdmin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", esAdmin=" + esAdmin +
                '}';
    }
}