package edu.eci.arsw.treecore.services;

import java.util.ArrayList;


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
public interface TreeCoreUserServices {
	public Usuario getUsuario(String correo) throws ServiciosTreeCoreException;
	public Usuario verificarCredenciales(String correo, String contraseña) throws ServiciosTreeCoreException;
	public void setUser(String correo, String passwd) throws ServiciosTreeCoreException;
	public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException;
	public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException;
	public Proyecto getProyecto(int identificador);
	public ArrayList<Usuario> getParticipantes(int identificador);
	public boolean estaParticipando (String correo, int identificador);
	public ArrayList<Rama> getRamas (int identificador);
	public ArrayList<Mensaje> getMensajes (int identificador);
}
