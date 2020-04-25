package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Mensaje;

public interface ProyectoMapper {

	/**
	 * Metodo que retorna una lista de todos los proyectos
	 * 
	 * @return Lista que contiene todos los proyectos
	 */
	public ArrayList<Proyecto> getProyectos();

	/**
	 * Metodo que retorna un proyecto dado su id
	 * 
	 * @param id Id del proyecto
	 * @return Proyecto solicitado
	 */
	public Proyecto getProyecto(@Param("id") int id);

	/**
	 * Metodo que retorna un proyecto dado su nombre
	 * 
	 * @param projectName Nombre del proyecto
	 * @return Proyecto solicitado
	 */
	public Proyecto getProyectoByName(@Param("projectName") String projectName);

	/**
	 * Metodo que retorna los proyectos pertenecientes a un usuario
	 * 
	 * @param correo Correo del usuario
	 * @return Lista de los proyectos pertenecientes al usuario
	 */
	public ArrayList<Proyecto> getProyectosUsuario(@Param("correo") String correo);

	/**
	 * Metodo que adiciona un nuevo proyecto
	 * 
	 * @param proyecto Nuevo proyecto
	 */
	public void insertarProyecto(@Param("proyecto") Proyecto proyecto);

	/**
	 * Metodo que adiciona un nuevo miembro al proyecto
	 * 
	 * @param usuario  Nuevo usuario
	 * @param proyecto Proyecto al que va a pertenecer el usuario
	 */
	public void insertarParticipante(@Param("usuario") Usuario usuario, @Param("proyecto") Proyecto proyecto);

	/**
	 * Metodo para adicionar una nueva rama hija a un proyecto
	 * 
	 * @param rama     Nueva rama a adicionar
	 * @param proyecto Proyecto al que pertenece la rama
	 */
	public void insertarRamaConPadre(@Param("rama") Rama rama, @Param("proyecto") Proyecto proyecto);

	/**
	 * Metodo para adicionar una nueva rama aun proyecto
	 * 
	 * @param rama     Nueva rama a adicionar
	 * @param proyecto Proyecto al que pertenece la rama
	 */
	public void insertarRama(@Param("rama") Rama rama, @Param("proyecto") Proyecto proyecto);

	/**
	 * Metodo para actualizar los datos de una rama
	 * 
	 * @param proyecto Proyecto al que pertenece la rama
	 * @param rama     Rama con sus nuevos datos
	 */
	public void updateRama(@Param("project") Proyecto proyecto, @Param("rama") Rama rama);

	/**
	 * Metodo para adicionar un mensaje a un proyecto
	 * 
	 * @param mensaje  Nuevo mensaje
	 * @param proyecto Proyecto al que va a pertenecer el mensaje
	 */
	public void insertarMensaje(@Param("mensaje") Mensaje mensaje, @Param("proyecto") int proyecto);

}
