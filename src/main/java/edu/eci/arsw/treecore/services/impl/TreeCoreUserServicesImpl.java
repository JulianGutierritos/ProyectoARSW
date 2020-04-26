package edu.eci.arsw.treecore.services.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.persistenceDAO.UsuarioDAO;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;
import org.springframework.stereotype.Component;

@Component
public class TreeCoreUserServicesImpl implements TreeCoreUserServices {

    @Autowired
    UsuarioDAO usuarioDAO;
    
    public ArrayList<Usuario> getAllUsers() throws ServiciosTreeCoreException{
    	try {
			return this.usuarioDAO.getAllUsers();
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No se han podido cargar los usuarios");
		}
    }
    
    @Override
    public Usuario getUsuario(String correo) throws ServiciosTreeCoreException {
        try {
            return usuarioDAO.getUser(correo);
        }
        catch (PersistenceException e) {
            throw new ServiciosTreeCoreException("Usuario no encontrado");
        }
    }
    
    
    @Override
	public Usuario verificarCredenciales(String correo, String passwd) throws ServiciosTreeCoreException{
        try{
            return usuarioDAO.getUser(correo, passwd);
        }
        catch(PersistenceException e){
            throw new ServiciosTreeCoreException("Credenciales incorrectas");
        }
    }

    
    @Override
    public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException {
        try{
            return usuarioDAO.getNotificaciones(correo);
        }
        catch(PersistenceException e){
            throw new ServiciosTreeCoreException("Credenciales incorrectas");
        }
    }

    
    @Override
    public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException {
        try{
            return usuarioDAO.getInvitaciones(correo);
        }
        catch(PersistenceException e){
            throw new ServiciosTreeCoreException("Credenciales incorrectas");
        }
    }

    
	@Override
	public void addNewUser(Usuario user) throws ServiciosTreeCoreException {
		try {
			this.usuarioDAO.setUser(user);
		} 
		catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Credenciales incorrectas");
		}
		
    }
    
    @Override
	public void deleteInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException {
		try {
			this.usuarioDAO.deleteInvitacion(invitacion);
		} 
		catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Error al eliminar invitacion");
		}
		
    }
    
    @Override
    public void addInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException{
        try {
			this.usuarioDAO.insertarInvitacion(invitacion);
		} 
		catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Error al agregar invitacion");
		}
    }

}