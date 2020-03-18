package edu.eci.arsw.treecore.model.impl;

public class Invitacion {

    private String remitente; 
    private String proyecto;
    
    public Invitacion(String remitente, String proyecto){
        this.remitente = remitente;
        this.proyecto = proyecto;
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

    @Override
    public String toString(){
        return "{Remitente: " + remitente + " Proyecto: " + proyecto +"}";
    }

}