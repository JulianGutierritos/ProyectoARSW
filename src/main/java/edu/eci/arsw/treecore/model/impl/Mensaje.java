package edu.eci.arsw.treecore.model.impl;
import java.util.Date;

public class Mensaje {
    private Usuario usuario;
    private Date fecha; 
    private String contenido;
    
    public Mensaje(Usuario usuario, Date fecha, String contenido){
        this.usuario = usuario;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public Mensaje(){
        
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public String getContenido(){
        return contenido;
    }
    public void setContenido(String contenido){
        this.contenido = contenido;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setId(Usuario usuario){
        this.usuario = usuario;
    }

}