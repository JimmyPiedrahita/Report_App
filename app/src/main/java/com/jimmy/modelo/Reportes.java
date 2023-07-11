package com.jimmy.modelo;
public class Reportes {
    private String Nombre;
    private String Correo;
    private String Sede;
    private String Sala;
    private String Pc;
    private String Descripcion;
    private String Estado;
    private int Codigo;
    public Reportes(int co,String n,String c, String s, String sa, String p, String d,String e){
        this.Codigo = co;
        this.Nombre = n;
        this.Correo = c;
        this.Sede = s;
        this.Sala = sa;
        this.Pc = p;
        this.Descripcion = d;
        this.Estado = e;
    }
    public Reportes() {}
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String correo) {
        Correo = correo;
    }
    public String getSede() {
        return Sede;
    }
    public void setSede(String sede) {
        Sede = sede;
    }
    public String getSala() {
        return Sala;
    }
    public void setSala(String sala) {
        Sala = sala;
    }
    public String getPc() {
        return Pc;
    }
    public void setPc(String pc) {
        Pc = pc;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public int getCodigo() {
        return Codigo;
    }
    public void setCodigo(int codigo) {
        this.Codigo = codigo;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
}