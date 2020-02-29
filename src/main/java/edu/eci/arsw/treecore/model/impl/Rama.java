package edu.eci.arsw.treecore.model.impl;

import java.util.ArrayList;
import java.util.Date;

public class Rama {
    private String nombre;
    private Rama ramaPadre;
    private ArrayList<Archivo> archivos;
    private Date fechaDeCreacion;
    public Rama(String nombre, Rama ramaPadre, ArrayList<Archivo> archivos, Date fechaDeCreacion){
        this.nombre = nombre;
        this.ramaPadre = ramaPadre;
        this.archivos = archivos;
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
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
