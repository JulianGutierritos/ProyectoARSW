package edu.eci.arsw.treecore.services;

import java.util.ArrayList;
import java.util.Set;

import edu.eci.arsw.treecore.exceptions.ProjectNotFoundException;
import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;

import org.springframework.stereotype.Service;

//import edu.eci.arsw.treecore.persistence.TreeCorePersistence;

@Service
public interface TreeCoreServices {
	public Usuario getUsuario(String correo) throws ServiciosTreeCoreException;
	public Usuario verificarCredenciales(String correo, String contraseña) throws ServiciosTreeCoreException;
	public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException;
	public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException;
	public Proyecto getProyecto(int identificador);
	public Proyecto getProyecto(String Creator, String Name) throws ProjectNotFoundException;
	public ArrayList<Proyecto> getAllProyectos() throws ProjectNotFoundException;
	public ArrayList<Proyecto> getAllProyectosUser() throws ProjectNotFoundException;
	public ArrayList<Usuario> getParticipantes(int identificador);
	public boolean estaParticipando (String correo, int identificador);
	public ArrayList<Rama> getRamas (int identificador);
	public ArrayList<Mensaje> getMensajes (int identificador);
}
