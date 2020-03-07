package edu.eci.arsw.treecore.model.impl;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private String correo;
    private String nombre;
    private String contraseña;
    private ArrayList<Notificacion> notificaciones;
    private ArrayList<Invitacion> invitaciones;

    public Usuario(String correo, String nombre, String contraseña, ArrayList<Notificacion> notificaciones, ArrayList<Invitacion> invitaciones, Date ultimoAcceso) {
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.notificaciones = notificaciones;
        this.invitaciones = invitaciones;
    }

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getContraseña(){
        return contraseña;
    }
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    public ArrayList<Notificacion> getNoticaciones(){
        return notificaciones;
    }  
    public void setNoificaciones(ArrayList<Notificacion> notificaciones){
        this.notificaciones = notificaciones;
    }
    public ArrayList<Invitacion> getInvitaciones(){
        return invitaciones;
    }
    public void setInvitaciones(ArrayList<Invitacion> invitaciones){
        this.invitaciones = invitaciones;
    }
}
