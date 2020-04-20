package edu.eci.arsw.treecore.services;

import java.util.ArrayList;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;
//import edu.eci.arsw.treecore.persistence.TreeCorePersistence;

public interface TreeCoreProjectServices {

	public Proyecto getProyecto(int identificador) throws ServiciosTreeCoreException;

	public ArrayList<Proyecto> getAllProyectos() throws ServiciosTreeCoreException;

	public ArrayList<Proyecto> getAllProyectosUser(String correo) throws ServiciosTreeCoreException;

	public ArrayList<Usuario> getParticipantes(int identificador) throws ServiciosTreeCoreException;

	public boolean estaParticipando(String correo, int identificador) throws ServiciosTreeCoreException;

	public ArrayList<Rama> getRamas(int identificador) throws ServiciosTreeCoreException;

	public Rama getSpecificProjectRama(int idProyecto, int idRama) throws ServiciosTreeCoreException;

	public ArrayList<Mensaje> getMensajes(int identificador) throws ServiciosTreeCoreException;

	public void insertarProyecto(Proyecto proyecto) throws ServiciosTreeCoreException;

	public void insertarParticipante(Usuario usuario, Proyecto proyecto) throws ServiciosTreeCoreException;

	public void insertarRama(Rama rama, Proyecto proyecto) throws ServiciosTreeCoreException;

	public void insertarMensaje(Mensaje mensaje, int proyecto) throws ServiciosTreeCoreException;

	public Proyecto getProyectoByName(String projectName) throws ServiciosTreeCoreException;
	
	public void upateRama(Proyecto proyecto, Rama rama) throws ServiciosTreeCoreException;
}

