package edu.eci.arsw.treecore.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.persistenceDAO.ProyectoDAO;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;

@Service
public class TreeCoreProjectServicesImpl implements TreeCoreProjectServices {

    @Autowired
    ProyectoDAO proyectoDAO;

    @Override
    public Proyecto getProyecto(int identificador) throws ServiciosTreeCoreException {
        Proyecto p;
        try {
            p = proyectoDAO.getProyecto(identificador);
        } catch (PersistenceException e) {
            throw new ServiciosTreeCoreException("Proyecto no encontrado");
        }
        return p;
    }

    @Override
    public ArrayList<Proyecto> getAllProyectos() throws ServiciosTreeCoreException { 
        ArrayList<Proyecto> proyectos;
        // Proyecto p1 = new Proyecto(5,"Prueba", "Un proyecto de prueba", null, null, null, null, null);
        // proyectos.add(p1);
        try {
        	proyectos = proyectoDAO.getProyectos();
        	return proyectoDAO.getProyectos();
            
        } 
        catch (PersistenceException e) {
            throw new ServiciosTreeCoreException("No hay proyectos");
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectosUser(String correo) throws ServiciosTreeCoreException {
        ArrayList<Proyecto> proyectos;
        try {
            proyectos = proyectoDAO.getProyectosUsuario(correo);
        } catch (PersistenceException e) {
            throw new ServiciosTreeCoreException("No hay proyectos");
        }
        return proyectos;
    }

    @Override
    public ArrayList<Usuario> getParticipantes(int identificador) throws ServiciosTreeCoreException {
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

}