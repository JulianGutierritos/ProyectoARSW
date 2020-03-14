package edu.eci.arsw.treecore.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.eci.arsw.treecore.exceptions.ProjectNotFoundException;
import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;

@Service
public class TreeCoreProjectServicesImpl implements TreeCoreProjectServices {

    @Override
    public Proyecto getProyecto(int identificador) throws ProjectNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Proyecto> getAllProyectos() throws ProjectNotFoundException { 
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>(); 
        Proyecto p1 = new Proyecto(5,"Prueba", "Un proyecto de prueba", null, null, null, null, null);
        proyectos.add(p1);
        return proyectos;
    }

    @Override
    public ArrayList<Proyecto> getAllProyectosUser() throws ProjectNotFoundException {
        return null;
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