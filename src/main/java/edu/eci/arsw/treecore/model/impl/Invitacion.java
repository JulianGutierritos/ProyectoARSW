package edu.eci.arsw.treecore.model.impl;

public class Invitacion {

    private String remitente; 
    private String proyecto;
    private String nombreProyecto;
    public Invitacion(String remitente, String proyecto, String nombreProyecto){
        this.remitente = remitente;
        this.proyecto = proyecto;
        this.nombreProyecto = nombreProyecto;
    }

    public Invitacion(){
        
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    @Override
    public String toString(){
        return "{Remitente: " + remitente + " Proyecto: " + proyecto + " Nombre del proyecto: " + nombreProyecto + "}";
    }

}