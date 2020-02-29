package edu.eci.arsw.treecore.model.impl;

import java.util.ArrayList;
import java.util.Date;

public class Proyecto {
    private String nombre;
    private String descripcion;
    private Usuario creador;
    private Date fechaDeCreacion;
    private ArrayList<Usuario> participantes;
    private ArrayList<Rama> ramas;
    public Proyecto(String nombre, String descripcion, Usuario creador, Date fechaDeCreacion, ArrayList<Usuario> participantes, ArrayList<Rama> ramas){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.fechaDeCreacion = fechaDeCreacion;
        this.participantes = participantes;
        this.ramas = ramas;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public Usuario getCreador(){
        return creador;
    }
    public void setCreador(Usuario creador){
        this.creador = creador;
    }
    public Date getFechaDeCreacion(){
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(Date fechaDeCreacion){
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public ArrayList<Usuario> getParticipantes(){
        return participantes;   
    }
    public void setParticipantes(ArrayList<Usuario> participantes){
        this.participantes = participantes;
    }
    public ArrayList<Rama> getRamas(){
        return ramas;   
    }
    public void setRamas(ArrayList<Rama> ramas){
        this.ramas = ramas;
    }
}
