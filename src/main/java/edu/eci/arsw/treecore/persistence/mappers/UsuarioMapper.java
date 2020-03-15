package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;

public interface UsuarioMapper {
    
    public ArrayList<Usuario> getUsers();
    public Usuario getUser(@Param("correo") String correo, @Param("contraseña") String passwd);
    public Usuario getUser2(@Param("correo") String correo); 
    public void getUserWithPasswd(@Param("correo") String correo, @Param("passwd") String passwd);
    public ArrayList<Notificacion> getNotificaciones(@Param("correo") String correo);

}
