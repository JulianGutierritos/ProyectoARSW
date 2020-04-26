package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;

public interface UsuarioMapper {

	/**
	 * Metodo que retorna todos los usuarios registrados
	 * 
	 * @return Lista de los usuarios
	 */
	public ArrayList<Usuario> getUsers();

	/**
	 * Metodo que retorna el usuario al que le pertenece el correo
	 * 
	 * @param correo Correo del usuario a buscar
	 * @return Usuario correspondiente
	 */
	public Usuario getUser(@Param("correo") String correo);

	/**
	 * Metodo que retorna el usuario al que le pertenece el correo y la respectiva
	 * contraseña
	 * 
	 * @param correo Correo del usuario a buscar
	 * @param passwd Contraseña del usuario a buscar
	 * @return
	 */
	public Usuario getUserWithPasswd(@Param("correo") String correo, @Param("passwd") String passwd);

	/**
	 * Metodo que retorna las notificaciones pertenecientes al correo dado
	 * 
	 * @param correo Correo del usuario
	 * @return Lista de notificaciones del usaurio
	 */
	public ArrayList<Notificacion> getNotificaciones(@Param("correo") String correo);

	/**
	 * Metodo que adiciona un nuevo usuario
	 * 
	 * @param u Nuevo usuario
	 */
	public void insertarUsuario(@Param("usuario") Usuario u);

	/**
	 * Metodo que adiciona notificaciones al usuario dado
	 * 
	 * @param n Notificaciones del usuario
	 * @param u Usuario
	 */
	public void insertarNotificacion(@Param("notificacion") Notificacion n, @Param("usuario") Usuario u);

	/**
	 * Metodo que adiciona una invitacion al usuario dado
	 * 
	 * @param i Invitacion del usuario
	 */
	public void insertarInvitacion(@Param("invitacion") Invitacion i);

	/**
	 * Metodo que cambia la contraseña al usuario dado
	 * 
	 * @param u      Usuario
	 * @param passwd Nueva contaseña del usuario
	 */
	public void setContraseña(@Param("usuario") Usuario u, @Param("passwd") String passwd);

	/**
	 * Metodo que cambia del nombre del usuario dado
	 * 
	 * @param u      Usuario
	 * @param nombre Nuevo nombre del usuario
	 */
	public void setNombre(@Param("usuario") Usuario u, @Param("nombre") String nombre);

	/**
	 * Metodo que cambia del correo del usuario dado
	 * 
	 * @param u      Usuario
	 * @param correo Nuevo correo del usuario
	 */
	public void setCorreo(@Param("usuario") Usuario u, @Param("correo") String correo);

	/**
	 * Metodo que elimina el usuario dado de la bd
	 * 
	 * @param u Usuario
	 */
	public void deleteUsuario(@Param("usuario") Usuario u);

	/**
	 * Metodo que elimina las notificaciones del usuario dado
	 * 
	 * @param u Usuario
	 */
	public void deleteNotificaciones(@Param("usuario") Usuario u);

	/**
	 * Metodo que elimina una invitacion del usuario dado
	 * 
	 * @param i Invitacion
	 */
	public void deleteInvitacion(@Param("invitacion") Invitacion i);

	/**
	 * Metodo que elimina todas las invitaciones del usuario dado
	 * 
	 * @param u Usuario
	 */
	public void deleteInvitaciones(@Param("usuario") Usuario u);

}
