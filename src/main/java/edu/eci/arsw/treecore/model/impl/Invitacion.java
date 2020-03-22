package edu.eci.arsw.treecore.model.impl;

public class Invitacion {

    private String remitente; 
    private int proyecto;
    private String nombreProyecto;
    private String receptor;
    public Invitacion(String remitente, int proyecto, String nombreProyecto, String receptor){
        this.remitente = remitente;
        this.proyecto = proyecto;
        this.nombreProyecto = nombreProyecto;
        this.receptor = receptor;
    }

    public Invitacion(){
        
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
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