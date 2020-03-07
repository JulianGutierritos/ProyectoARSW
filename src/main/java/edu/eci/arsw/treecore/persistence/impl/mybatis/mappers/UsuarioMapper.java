package edu.eci.arsw.treecore.persistence.impl.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;

public interface UsuarioMapper {
	
    public Usuario getUser(@Param("correo") String correo, @Param("contraseña") String contraseña);
    public Usuario getUser2(@Param("correo") String correo); 
    public ArrayList<Notificacion> getNotificaciones(@Param("correo") String correo);

}
