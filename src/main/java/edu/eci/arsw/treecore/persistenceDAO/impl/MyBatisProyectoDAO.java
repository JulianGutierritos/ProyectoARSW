package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.persistence.mappers.ProyectoMapper;
import edu.eci.arsw.treecore.persistenceDAO.ProyectoDAO;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisProyectoDAO implements ProyectoDAO {

    @Autowired
	private ProyectoMapper proyectoMapper;

    @Override
    public Proyecto getProyecto(int identificador) throws PersistenceException {
        Proyecto proyecto=proyectoMapper.getProyecto(identificador);
		if(proyecto==null) throw new PersistenceException("Proyecto no encontrado");
		else return proyecto;
    }

    @Override
    public ArrayList<Proyecto> getProyectos() throws PersistenceException {
    
        return null;
    }

    @Override
    public ArrayList<Proyecto> getProyectosUsuario(String correo) throws PersistenceException {
        ArrayList<Proyecto> proyectos = proyectoMapper.getProyectosUsuario(correo);
		if(proyectos==null) throw new PersistenceException("El usuario no tiene proyectos");
        else return proyectos;
    }

    @Override
    public ArrayList<Usuario> getParticipantes(int identificador) throws PersistenceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean estaParticipando(String correo, int identificador) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Rama> getRamas(int identificador) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Mensaje> getMensajes(int identificador) {
        // TODO Auto-generated method stub
        return null;
    }
}