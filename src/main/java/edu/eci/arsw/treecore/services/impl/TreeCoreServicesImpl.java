package edu.eci.arsw.treecore.services.impl;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.exceptions.ProjectNotFoundException;
import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.persistence.UsuarioDAO;
import edu.eci.arsw.treecore.services.TreeCoreServices;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

//import edu.eci.arsw.treecore.persistence.TreeCorePersistence;

@Service
public class TreeCoreServicesImpl implements TreeCoreServices {

    @Autowired
    @Qualifier("MyBatisUsuario")
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
    public Usuario verificarCredenciales(String correo, String contraseña) throws ServiciosTreeCoreException {
        Usuario user;
        try {
            user = usuarioDAO.getUser(correo, contraseña);
        } catch (PersistenceException e) {
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

    @Override
    public Proyecto getProyecto(int identificador) {
        return null;
    }

    @Override
    public ArrayList<Usuario> getParticipantes(int identificador) {
        return null;
    }

    @Override
    public boolean estaParticipando(String correo, int identificador) {
        return false;
    }

    @Override
    public ArrayList<Rama> getRamas(int identificador) {
        return null;
    }

    @Override
    public ArrayList<Mensaje> getMensajes(int identificador) {
        return null;
    }

    @Override
    public ArrayList<Proyecto> getAllProyectos() throws ProjectNotFoundException{
        return null;
    }

    @Override
    public Proyecto getProyecto(String Creator, String Name) throws ProjectNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Proyecto> getAllProyectosUser() throws ProjectNotFoundException {
        return null;
    }
}