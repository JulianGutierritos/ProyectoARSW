package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.persistence.mappers.UsuarioMapper;
import edu.eci.arsw.treecore.persistenceDAO.UsuarioDAO;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUsuarioDAO implements UsuarioDAO{
	
	@Autowired
	private UsuarioMapper usuarioMapper;   
	
	
	@Override
	public ArrayList<Usuario> getAllUsers() throws PersistenceException {
		ArrayList<Usuario> users=this.usuarioMapper.getUsers();
		if(users==null) throw new PersistenceException("Error al encontrar los usuarios");
		else return users;   
	}
	
	
	@Override
	public Usuario getUser(String correo, String passwd) throws PersistenceException {
		Usuario user=usuarioMapper.getUserWithPasswd(correo, passwd);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else return user;   
	}
	
	
	@Override
	public Usuario getUser(String correo) throws PersistenceException {
		Usuario user=usuarioMapper.getUser(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else return user;   
	}
	
	
	@Override
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException{
		ArrayList<Notificacion> n;
		Usuario user=usuarioMapper.getUser(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else n = user.getNotificaciones();
		return n; 
	} 
	
	
	@Override
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException{
		ArrayList<Invitacion> n;
		Usuario user=usuarioMapper.getUser(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else n = user.getInvitaciones();
		return n; 
	}
	
	@Override
	public void setUser(Usuario u) throws PersistenceException{
		try {
			this.usuarioMapper.insertarUsuario(u);
		}
		catch(Exception e) {
			throw new PersistenceException("No se ha podido adicionar al usuario");
		}
	}

	@Override
	public void deleteInvitacion(Invitacion invitacion) throws PersistenceException{
		try {
			this.usuarioMapper.deleteInvitacion(invitacion);
		}
		catch(Exception e) {
			throw new PersistenceException("No se ha podido adicionar al usuario");
		}
	}


	

}