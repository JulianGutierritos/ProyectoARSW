package edu.eci.arsw.treecore.persistence;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.exceptions.PersistenceException;

@Service
public interface UsuarioDAO {
	
	public Usuario getUser(String correo, String contraseña) throws PersistenceException;
	public Usuario getUser(String correo) throws PersistenceException;
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException; 
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException;
}

