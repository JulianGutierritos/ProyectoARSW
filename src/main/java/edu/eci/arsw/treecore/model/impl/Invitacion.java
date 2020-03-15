package edu.eci.arsw.treecore.model.impl;

public class Invitacion {
    private Usuario remitente; 
    private Proyecto proyecto;
    
    public Invitacion(Usuario remitente, Proyecto proyecto){
        this.remitente = remitente;
        this.proyecto = proyecto;
    }

    public Invitacion(){
        
    }

    public Usuario getRemitente(){
        return remitente;
    }

    public void setRemitente(Usuario remitente){
        this.remitente = remitente;
    }

    public Proyecto getProyecto(){
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto){
        this.proyecto = proyecto;
    }

}