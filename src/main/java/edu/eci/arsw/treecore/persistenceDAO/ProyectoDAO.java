package edu.eci.arsw.treecore.persistenceDAO;

import java.util.ArrayList;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.exceptions.PersistenceException;

public interface ProyectoDAO {

	/**
	 * Metodo que retorna un proyecto dado su id
	 * 
	 * @param identificador Id del proyecto
	 * @return Proyecto según el identificador dado
	 * @throws PersistenceException Si no encuentra el proyecto
	 */
	public Proyecto getProyecto(int identificador) throws PersistenceException;

	/**
	 * Metodo que retorna todos los proyectos
	 * 
	 * @return Lista con todos los proyectos
	 * @throws PersistenceException Si no encuentra proyectos
	 */
	public ArrayList<Proyecto> getProyectos() throws PersistenceException;

	/**
	 * Metodo que retorna los proyectos pertenecientes a un usuario
	 * 
	 * @param correo Correo del usuario
	 * @return Lista con todos los proyectos del usuario
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public ArrayList<Proyecto> getProyectosUsuario(String correo) throws PersistenceException;

	/**
	 * Metodo que retorna los usuarios pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con los usuarios pertenecientes al proyecto
	 * @throws PersistenceException Si no encuentra el proyecto
	 */
	public ArrayList<Usuario> getParticipantes(int identificador) throws PersistenceException;

	/**
	 * Metodo que dice si un usuario pertenece a un proyecto
	 * 
	 * @param correo        Correo del usuario
	 * @param identificador Id del proyecto
	 * @return true si el usuario pertenece; false en caso contrario
	 * @throws PersistenceException Si no encuentra al usuario
	 */
	public boolean estaParticipando(String correo, int identificador) throws PersistenceException;

	/**
	 * Metodo que retorna todas las ramas pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con todas las ramas del proyecto
	 * @throws PersistenceException Si no encuentra al proyecto
	 */
	public ArrayList<Rama> getRamas(int identificador) throws PersistenceException;

	/**
	 * Metodo que retorna los mensajes pertenecientes a un proyecto
	 * 
	 * @param identificador Id del proyecto
	 * @return Lista con los mensajes del proyecto
	 * @throws PersistenceException Si no encuentra al proyecto
	 */
	public ArrayList<Mensaje> getMensajes(int identificador) throws PersistenceException;

	/**
	 * Metodo que adiciona un nuevo miembro al proyecto
	 * 
	 * @param proyecto Proyecto del nuevo miembro
	 * @param usuario  Usuario que va a ser miembro del proyecto
	 * @throws PersistenceException Si no puede insertar al usuario
	 */
	public void insertarParticipante(Proyecto proyecto, Usuario usuario) throws PersistenceException;

	/**
	 * Metodo para adicionar una nueva rama a un proyecto
	 * 
	 * @param rama     Nueva rama
	 * @param proyecto Proyecto al que va a pertenecer la rama
	 * @throws PersistenceException Si no puede insertar la rama
	 */
	public void insertarRama(Rama rama, Proyecto proyecto) throws PersistenceException;

	/**
	 * Metodo para eliminar una rama
	 * 
	 * @param rama Rama a eliminar
	 * @throws PersistenceException Si no encuentra a la rama
	 */
	public void deleteRama(Rama rama) throws PersistenceException;

	/**
	 * Metodo para adicionar un nuevo proyecto
	 * 
	 * @param proyecto Nuevo proyecto
	 * @throws PersistenceException Si no puede insertar el nuevo proyecto
	 */
	public void insertarProyecto(Proyecto proyecto) throws PersistenceException;

	/**
	 * Metodo para adicionar un nuevo mensaje a un proyecto
	 * 
	 * @param mensaje  Nuevo mensaje
	 * @param proyecto Proyecto al que le pertenecera el mensaje
	 * @throws PersistenceException Si no encuentra al proyecto
	 */
	public void insertarMensaje(Mensaje mensaje, int proyecto) throws PersistenceException;

	/**
	 * Metodo que retorna un proyecto dado su nombre
	 * 
	 * @param projectName Nombre del proyecto
	 * @return Proyecto solicitado
	 * @throws PersistenceException Si no encuentra al proyecto
	 */
	public Proyecto getProyectoByName(String projectName) throws PersistenceException;

	/**
	 * Metodo que actualiza los datos de una rama
	 * 
	 * @param proyecto Proyecto al que pertenece una rama
	 * @param rama     Rama con los nuevos datos
	 * @throws PersistenceException Si no encuentra a la rama o el proyecto
	 */
	public void updateRama(Proyecto proyecto, Rama rama) throws PersistenceException;

	/**
	 * Metodo que obtiene el token de la app
	 * 
	 * @param tokenId id del token en la base de datos.
	 * @return
	 */
	public String getAccessToken(int tokenId) throws PersistenceException;

	/**
	 * Metodo para obtener el ultimo id de rama.
	 * 
	 */
	public int getLastBranchId();

}
