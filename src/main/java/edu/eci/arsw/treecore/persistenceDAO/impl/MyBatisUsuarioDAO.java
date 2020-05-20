package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.persistence.mappers.UsuarioMapper;
import edu.eci.arsw.treecore.persistenceDAO.TeeCoreCacheService;
import edu.eci.arsw.treecore.persistenceDAO.UsuarioDAO;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisUsuarioDAO implements UsuarioDAO {

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Autowired
	private TeeCoreCacheService treeCoreCacheService;

	@Override
	public ArrayList<Usuario> getAllUsers() throws PersistenceException {
		ArrayList<Usuario> users = this.usuarioMapper.getUsers();
		treeCoreCacheService.insertarUsuarios(users);
		if (users == null)
			throw new PersistenceException("Error al encontrar los usuarios");
		else
			return users;
	}

	@Override
	public Usuario getUser(String correo, String passwd) throws PersistenceException {
		Usuario user = treeCoreCacheService.getUsuario(correo);
		if (user != null) {
			if (user.getPasswd().equals(passwd)) {
				return user;
			} else {
				throw new PersistenceException("Usuario no encontrado");
			}
		} else {
			user = usuarioMapper.getUserWithPasswd(correo, passwd);
			if (user == null)
				throw new PersistenceException("Usuario no encontrado");
			else {
				treeCoreCacheService.insertarUsuario(user);
				return user;
			}
		}
	}

	@Override
	public Usuario getUser(String correo) throws PersistenceException {
		Usuario user = treeCoreCacheService.getUsuario(correo);
		if (user != null) {
			return user;
		} else {
			user = usuarioMapper.getUser(correo);
			if (user == null)
				throw new PersistenceException("Usuario no encontrado");
			else {
				treeCoreCacheService.insertarUsuario(user);
				return user;
			}
		}
	}

	@Override
	public ArrayList<Notificacion> getNotificaciones(String correo) throws PersistenceException {
		Usuario user = treeCoreCacheService.getUsuario(correo);
		if (user != null) {
			return user.getNotificaciones();
		} else {
			user = usuarioMapper.getUser(correo);
			if (user == null)
				throw new PersistenceException("Usuario no encontrado");
			else {
				treeCoreCacheService.insertarUsuario(user);
				return user.getNotificaciones();
			}
		}
	}

	@Override
	public void insertarNotifiacion(Notificacion notificacion, String correo) throws PersistenceException {
		try {
			int i = this.usuarioMapper.insertarNotificacion(notificacion, correo);
			notificacion.setId(i);
			Date currentDate = new Date();
			notificacion.setFecha(currentDate);
			treeCoreCacheService.insertarNotificacion(notificacion, correo);
		} catch (Exception e) {
			throw new PersistenceException("Usuario no encontrado");
		}
	}

	@Override
	public ArrayList<Invitacion> getInvitaciones(String correo) throws PersistenceException {
		Usuario user = treeCoreCacheService.getUsuario(correo);
		if (user != null) {
			return user.getInvitaciones();
		} else {
			user = usuarioMapper.getUser(correo);
			if (user == null)
				throw new PersistenceException("Usuario no encontrado");
			else {
				treeCoreCacheService.insertarUsuario(user);
				return user.getInvitaciones();
			}
		}
	}

	@Override
	public void setUser(Usuario u) throws PersistenceException {
		try {
			this.usuarioMapper.insertarUsuario(u);
			treeCoreCacheService.insertarUsuario(u);
		} catch (Exception e) {
			throw new PersistenceException("No se ha podido adicionar al usuario");
		}
	}

	@Override
	public void deleteInvitacion(Invitacion invitacion) throws PersistenceException {
		try {
			this.usuarioMapper.deleteInvitacion(invitacion);
			treeCoreCacheService.eliminarInvitacion(invitacion, invitacion.getReceptor());
		} catch (Exception e) {
			throw new PersistenceException("No se ha podido eliminar la invitacion");
		}
	}

	@Override
	public void insertarInvitacion(Invitacion invitacion) throws PersistenceException {
		try {
			this.usuarioMapper.insertarInvitacion(invitacion);
			treeCoreCacheService.insertarInvitacion(invitacion, invitacion.getReceptor());
		} catch (Exception e) {
			throw new PersistenceException("No se ha podido adicionar la invitacion");
		}
	}
}