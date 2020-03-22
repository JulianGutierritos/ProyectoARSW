package edu.eci.arsw.treecore.services;

import java.util.ArrayList;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Usuario;

public interface TreeCoreUserServices {
	public ArrayList<Usuario> getAllUsers() throws ServiciosTreeCoreException;
	public Usuario getUsuario(String correo) throws ServiciosTreeCoreException;
	public Usuario verificarCredenciales(String correo, String contraseña) throws ServiciosTreeCoreException;
	public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException;
	public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException;
	public void addNewUser(Usuario User) throws ServiciosTreeCoreException;
	public void deleteInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException;
}
