package edu.eci.arsw.treecore.model.impl;

import java.util.ArrayList;

public class Usuario {
    private String correo;
    private String nombre;
    private String passwd;
    private ArrayList<Notificacion> notificaciones;
    private ArrayList<Invitacion> invitaciones;

    public Usuario(String correo, String nombre, String passwd, ArrayList<Notificacion> notificaciones, ArrayList<Invitacion> invitaciones) {
        this.correo = correo;
        this.nombre = nombre;
        this.passwd = passwd;
        this.notificaciones = notificaciones;
        this.invitaciones = invitaciones;
    }

    public Usuario() {
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
    public String getPasswd(){
        return passwd;
    }
    public void setContraseña(String passwd){
        this.passwd = passwd;
    }
    public ArrayList<Notificacion> getNotificaciones(){
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

    @Override
    public String toString(){
        return "{Correo: "+correo + " Nombre: "+ nombre + " Passwd: "+ passwd + " Invitaciones: " + invitaciones + " Notificaciones: " + notificaciones +"}";
    }
}
