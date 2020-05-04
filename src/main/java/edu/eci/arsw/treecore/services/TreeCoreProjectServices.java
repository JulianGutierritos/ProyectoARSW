package edu.eci.arsw.treecore.services;

import java.util.ArrayList;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;

public interface TreeCoreProjectServices {

	/**
	 * Metodo que retorna un proyecto dado su id
	 * 
	 * @param identificador Id del proyecto
	 * @return Proyecto según el identificador dado
	 * @throws ServiciosTreeCoreException Si no encuentra el proyecto
	 */
	public Proyecto getProyecto(int identificador) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna una lista de todos los proyectos
	 * 
	 * @return Lista que contiene todos los proyectos
	 * @throws ServiciosTreeCoreException Si no enceuntra proyectos
	 */
	public ArrayList<Proyecto> getAllProyectos() throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna los proyectos pertenecientes a un usuario
	 * 
	 * @param correo Correo del usuario
	 * @return Lista con todos los proyectos del usuario
	 * @throws ServiciosTreeCoreException Si no encuentra al usuario
	 */
	public ArrayList<Proyecto> getAllProyectosUser(String correo) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna los usuarios pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con los usuarios pertenecientes al proyecto
	 * @throws ServiciosTreeCoreException Si no encuentra el proyecto
	 */
	public ArrayList<Usuario> getParticipantes(int identificador) throws ServiciosTreeCoreException;

	/**
	 * Metodo que dice si un usuario pertenece a un proyecto
	 * 
	 * @param correo        Correo del usuario
	 * @param identificador Id del proyecto
	 * @return true si el usuario pertenece; false en caso contrario
	 * @throws ServiciosTreeCoreException Si no encuentra al usuario
	 */
	public boolean estaParticipando(String correo, int identificador) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna todas las ramas pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con todas las ramas del proyecto
	 * @throws ServiciosTreeCoreException Si no encuentra al proyecto
	 */
	public ArrayList<Rama> getRamas(int identificador) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna la rama perteneciente al proyecto dados
	 * 
	 * @param idProyecto Id del proyecto
	 * @param idRama     Id de la rama
	 * @return Rama correspondiente
	 * @throws ServiciosTreeCoreException Si no encuentra la rama o el proyecto
	 */
	public Rama getSpecificProjectRama(int idProyecto, int idRama) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna los mensajes pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con los mensajes del proyecto
	 * @throws ServiciosTreeCoreException Si no encuentra al proyecto
	 */
	public ArrayList<Mensaje> getMensajes(int identificador) throws ServiciosTreeCoreException;

	/**
	 * Metodo para adicionar un nuevo mensaje a un proyecto
	 * 
	 * @param proyecto Nuevo proyecto
	 * @throws ServiciosTreeCoreException Si no puede insertar el nuevo proyecto
	 */
	public void insertarProyecto(Proyecto proyecto) throws ServiciosTreeCoreException;

	/**
	 * Metodo para eliminar un proyecto
	 * @param project Proyecto a eliminar
	 * @throws ServiciosTreeCoreException Si no puede eliminar el proyecto
	 */
	public void deleteProyecto(Proyecto project) throws ServiciosTreeCoreException;
	
	/**
	 * Metodo que adiciona un nuevo miembro al proyecto
	 * 
	 * @param usuario  Usuario que va a ser miembro del proyecto
	 * @param proyecto Proyecto del nuevo miembro
	 * @throws ServiciosTreeCoreException Si no puede insetar al usuario
	 */
	public void insertarParticipante(Usuario usuario, Proyecto proyecto) throws ServiciosTreeCoreException;

	/**
	 * Metodo para adicionar una nueva rama a un proyecto
	 * 
	 * @param rama     Nueva rama
	 * @param proyecto Proyecto al que va a pertenecer la rama
	 * @throws ServiciosTreeCoreException Si no puede insertar la rama
	 */
	public int insertarRama(Rama rama, Proyecto proyecto) throws ServiciosTreeCoreException;

	/**
	 * Metodo para eliminar una rama
	 * 
	 * @param rama Rama a eliminar
	 * @throws ServiciosTreeCoreException Si no encuentra la rama
	 */
	public void deleteRama(Rama rama) throws ServiciosTreeCoreException;

	/**
	 * Metodo para adicionar un nuevo mensaje a un proyecto
	 * 
	 * @param mensaje  Nuevo mensaje
	 * @param proyecto Proyecto al que le pertenecera el mensaje
	 * @throws ServiciosTreeCoreException Si no puede insertar la rama
	 */
	public void insertarMensaje(Mensaje mensaje, int proyecto) throws ServiciosTreeCoreException;

	/**
	 * Metodo que retorna un proyecto dado su nombre
	 * 
	 * @param projectName Nombre del proyecto
	 * @return Proyecto solicitado
	 * @throws ServiciosTreeCoreException Si no encuentra al proyecto
	 */
	public Proyecto getProyectoByName(String projectName) throws ServiciosTreeCoreException;

	/**
	 * Metodo que actualiza los datos de una rama
	 * 
	 * @param proyecto Proyecto al que pertenece una rama
	 * @param rama     Rama con los nuevos datos
	 * @throws ServiciosTreeCoreException Si no encuentra a la rama o el proyecto
	 */
	public void updateRama(Proyecto proyecto, Rama rama) throws ServiciosTreeCoreException;

	/**
	 * Metodo que obtiene el ultimo id de las ramas
	*/
	public int getLastBranchId();


}
