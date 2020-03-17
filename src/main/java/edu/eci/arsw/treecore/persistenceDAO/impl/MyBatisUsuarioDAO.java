package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.persistence.mappers.UsuarioMapper;
import edu.eci.arsw.treecore.persistenceDAO.UsuarioDAO;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUsuarioDAO implements UsuarioDAO{
	
	@Autowired
	private UsuarioMapper usuarioMapper;   

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
	public void setUser(String correo, String passwd) throws PersistenceException {
		this.usuarioMapper.getUserWithPasswd(correo, passwd);
	} 
	
	@Override
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException{
		ArrayList<Notificacion> n;
		Usuario user=usuarioMapper.getUser(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else n = user.getNoticaciones();
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

}