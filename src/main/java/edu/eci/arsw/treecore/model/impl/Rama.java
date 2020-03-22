package edu.eci.arsw.treecore.model.impl;


import java.util.ArrayList;
import java.util.Date;

public class Rama {
    private int id;
    private String nombre;
    private Rama ramaPadre;
    private ArrayList<Archivo> archivos;
    private Date fechaDeCreacion;
    private Usuario creador;
    public Rama(int id, String nombre, Rama ramaPadre, ArrayList<Archivo> archivos, Date fechaDeCreacion, Usuario creador){
        this.nombre = nombre;
        this.ramaPadre = ramaPadre;
        this.archivos = archivos;
        this.fechaDeCreacion = fechaDeCreacion;
        this.creador = creador;
        this.id = id;
    }

    public Rama(){
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Usuario getCreador(){
        return creador;
    }
    public void setCreador(Usuario creador){
        this.creador = creador;
    }
    public Rama getRamaPadre(){
        return ramaPadre;
    }
    public void setRamaPadre(Rama ramaPadre){
        this.ramaPadre = ramaPadre;
    }
    public ArrayList<Archivo> getArchivos(){
        return archivos;
    }
    public void setArchivos (ArrayList<Archivo> archivos){
        this.archivos = archivos;
    }
    public Date getFechaDeCreacion(){
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(Date fechaDeCreacion){
        this.fechaDeCreacion = fechaDeCreacion;
    }
}
