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

    @Override
    public Usuario getUsuario(String correo) throws ServiciosTreeCoreException {
        Usuario user;
        try {
            user = usuarioDAO.getUser(correo);
        } catch (PersistenceException e) {
            throw new ServiciosTreeCoreException("Usuario no encontrado");
        }
        return user;
    }
    
    @Override
    public void setUser(String correo, String passwd) throws ServiciosTreeCoreException{
    	try {
			this.usuarioDAO.setUser(correo, passwd);
		} 
    	catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Este usuario ya existe");
		}
    }
    
    @Override
	public Usuario verificarCredenciales(String correo, String passwd) throws ServiciosTreeCoreException{
        Usuario user;
        try{
            user=usuarioDAO.getUser(correo, passwd);
        }catch(PersistenceException e){
            throw new ServiciosTreeCoreException("Credenciales incorrectas");
        }
        return user;
    }

    @Override
    public ArrayList<Notificacion> getNotificaciones(String correo) throws ServiciosTreeCoreException {
        return null;
    }

    @Override
    public ArrayList<Invitacion> getInvitaciones(String correo) throws ServiciosTreeCoreException {
        return null;
    }

}