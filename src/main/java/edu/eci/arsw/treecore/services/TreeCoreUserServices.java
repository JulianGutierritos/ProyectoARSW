package edu.eci.arsw.treecore.services;

import java.util.ArrayList;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Usuario;

public interface TreeCoreUserServices {

	/**
	 * Metodo que retorna a todos los usuarios existentes
	 * 
	 * @return Lista que contiene a todos los usuarios
	 * @throws ServiciosTreeCoreException Si no puede obtener a los usuarios
	 */
	public ArrayList<Usuario> getAllUsers() throws ServiciosTreeCoreException;

	/**
	 * Metodo para obtener un usuario según su corres
	 * 
	 * @param correo Correo del usuario
	 * @return Usuario solicitado
	 * @throws ServiciosTreeCoreException Si no encuentra al usuario
	 */
	public Usuario getUsuario(String correo) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna al usuario segun su correo y su contraseña
	 * 
	 * @param correo Correo del usuario
	 * @param passwd Contraseña del usuario
	 * @return Usuario solicitado
	 * @throws ServiciosTreeCoreException Si no encuentra al usuario
	 */
	public Usuario verificarCredenciales(String correo, String passwd) throws ServiciosTreeCoreException;

	/**
	 * Metodo que las notificaciones del usuario segun su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Lista que contiene las notificaciones del usuario
	 * @throws ServiciosTreeCoreException Si no puede obtener las notificaciones -
	 *                                    No existe el usuario
	 */
	public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException;

	/**
	 * Metodo que las invitaciones del usuario segun su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Lista que contiene las invitaciones del usuario
	 * @throws ServiciosTreeCoreException Si no puede obtener las invitaciones - No
	 *                                    existe el usuario
	 */
	public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException;

	/**
	 * Metodo para adicionar un nuevo usuario
	 * 
	 * @param User Nuevo usuario
	 * @throws ServiciosTreeCoreException Si no puede agregar al usuario-Datos no
	 *                                    validos-Ya existe
	 */
	public void addNewUser(Usuario User) throws ServiciosTreeCoreException;

	/**
	 * Metodo para eliminar la invitacion de un usuario
	 * 
	 * @param invitacion Invitacion a eliminar
	 * @throws ServiciosTreeCoreException Si no se puede eliminar la invitacion-No
	 *                                    existe
	 */
	public void deleteInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException;

	/**
	 * Metodo para crear una invitacion
	 * 
	 * @param invitacion Nueva invitación
	 * @throws ServiciosTreeCoreException Si no puede crear la invitacion
	 */
	public void addInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException;

}
