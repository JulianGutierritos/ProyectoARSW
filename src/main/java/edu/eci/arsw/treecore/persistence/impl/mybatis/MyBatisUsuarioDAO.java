package edu.eci.arsw.treecore.persistence.impl.mybatis;

import edu.eci.arsw.treecore.persistence.UsuarioDAO;
import edu.eci.arsw.treecore.persistence.impl.mybatis.mappers.UsuarioMapper;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("MyBatisUsuario")
public class MyBatisUsuarioDAO implements UsuarioDAO{
	
    //@Autowired
	private UsuarioMapper usuarioMapper;   

	@Override
	public Usuario getUser(String correo, String contraseña) throws PersistenceException {
		Usuario user=usuarioMapper.getUser(correo, contraseña);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else return user;   
	}
	@Override
	public Usuario getUser(String correo) throws PersistenceException {
		Usuario user=usuarioMapper.getUser2(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else return user;   
	}
	@Override
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException{
		ArrayList<Notificacion> n;
		Usuario user=usuarioMapper.getUser2(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else n = user.getNoticaciones();
		return n; 
	} 
	@Override
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException{
		ArrayList<Invitacion> n;
		Usuario user=usuarioMapper.getUser2(correo);
		if(user==null) throw new PersistenceException("Usuario no encontrado");
		else n = user.getInvitaciones();
		return n; 
	} 

	


}