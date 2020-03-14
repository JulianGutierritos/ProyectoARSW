package edu.eci.arsw.treecore.persistenceDAO;

import java.util.ArrayList;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.exceptions.PersistenceException;

public interface UsuarioDAO {
	
	public Usuario getUser(String correo, String passwd) throws PersistenceException;
	public Usuario getUser(String correo) throws PersistenceException;
	public void setUser(String correo, String passwd) throws PersistenceException;
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException; 
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException;
	
}

