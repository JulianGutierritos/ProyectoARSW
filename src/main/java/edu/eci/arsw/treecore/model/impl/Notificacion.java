package edu.eci.arsw.treecore.model.impl;

import java.util.Date;

public class Notificacion {
    private Date fecha; 
    private String informacion;
    
    public Notificacion(Date fecha, String informacion){
        this.fecha = fecha;
        this.informacion = informacion;
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public String getInformacion(){
        return informacion;
    }

    public void setInformacion(String informacion){
        this.informacion = informacion;
    }

}