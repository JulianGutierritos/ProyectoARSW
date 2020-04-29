package edu.eci.arsw.treecore.persistenceDAO;

import java.util.ArrayList;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.exceptions.PersistenceException;

public interface UsuarioDAO {

	/**
	 * Metodo que retorna una lista con todos los usurios
	 * 
	 * @return Lista con los usuarios
	 * @throws PersistenceException Si no se pueden obtener los usuarios
	 */
	public ArrayList<Usuario> getAllUsers() throws PersistenceException;

	/**
	 * Metodo que retorna un usuario según su correo y contraseña
	 * 
	 * @param correo Correo del usuario
	 * @param passwd Contraseña del usuario
	 * @return El usuario correspondiente a las credenciales
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public Usuario getUser(String correo, String passwd) throws PersistenceException;

	/**
	 * Metodo que retorna un usuario según su correo
	 * 
	 * @param correo Correo del usuario
	 * @return El usuario correspondiente al correo
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public Usuario getUser(String correo) throws PersistenceException;

	/**
	 * Metodo que retorna las notificaciones de un usuario segun su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Lista de las notificaciones del usuario
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException;

	/**
	 * Inserta una nueva notifiacion
	 * 
	 * @param correo Correo del usuario
	 * @param notificacion notificacion a insertar
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public void insertarNotifiacion(Notificacion notificacion, String correo) throws PersistenceException;

	/**
	 * Metodo que retorna las invitaciones de un usuario segun su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Lista de las invitaciones del usuario
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException;

	/**
	 * Metodo que adiciona un nuevo usuario
	 * 
	 * @param u Nuevo usuario
	 * @throws PersistenceException Si el usuario no puede ser adicionado
	 *                              (credenciales no validas)
	 */
	public void setUser(Usuario u) throws PersistenceException;

	/**
	 * Metodo que elimina una inviación de un usuario
	 * 
	 * @param invitacion Invitacion a eliminar
	 * @throws PersistenceException Si no encuentra la invitacion
	 */
	public void deleteInvitacion(Invitacion invitacion) throws PersistenceException;

	/**
	 * Metodo que agrega una inviación de un usuario
	 * 
	 * @param invitacion Invitacion a eliminar
	 * @throws PersistenceException Si no encuentra la invitacion
	 */
	public void insertarInvitacion(Invitacion invitacion) throws PersistenceException;

}
