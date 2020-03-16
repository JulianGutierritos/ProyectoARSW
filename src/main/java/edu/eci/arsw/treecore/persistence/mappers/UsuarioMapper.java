package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;

public interface UsuarioMapper {
    
    public ArrayList<Usuario> getUsers();
    public Usuario getUser(@Param("correo") String correo);
    public Usuario getUserWithPasswd(@Param("correo") String correo, @Param("passwd") String passwd);
    public ArrayList<Notificacion> getNotificaciones(@Param("correo") String correo);
    public void insertarUsuario(@Param("usuario") Usuario u);
    public void insertarNotificacion(@Param("notificacion") Notificacion n, @Param("usuario") Usuario u);
    public void insertarInvitacion(@Param("invitacion") Invitacion i, @Param("usuario") Usuario u);
    public void setContraseña(@Param("usuario") Usuario u, @Param("passwd") String passwd);
    public void setNombre(@Param("usuario") Usuario u, @Param("nombre") String nombre);
    public void setCorreo(@Param("usuario") Usuario u, @Param("correo") String correo);
    public void deleteUsuario(@Param("usuario") Usuario u);
    public void deleteNotificaciones(@Param("usuario") Usuario u);
    public void deleteInvitacion(@Param("usuario") Usuario u, @Param("invitacion") Invitacion i);
	public void deleteInvitaciones(@Param("usuario") Usuario i);
}
