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
	public Proyecto getProyectoByName(String projectName) throws ServiciosTreeCoreException {
		try {
			Proyecto p = proyectoDAO.getProyectoByName(projectName);
			return p;
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Proyecto no encontrado");

		}
	}

	@Override
	public ArrayList<Proyecto> getAllProyectos() throws ServiciosTreeCoreException {
		ArrayList<Proyecto> proyectos;
		try {
			proyectos = proyectoDAO.getProyectos();
			return proyectoDAO.getProyectos();
		} catch (PersistenceException e) {
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
		ArrayList<Usuario> participantes;
		try {
			participantes = proyectoDAO.getParticipantes(identificador);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
		return participantes;
	}

	@Override
	public ArrayList<Mensaje> getMensajes(int identificador) throws ServiciosTreeCoreException {
		ArrayList<Mensaje> mensajes;
		try {
			mensajes = proyectoDAO.getMensajes(identificador);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
		return mensajes;
	}

	@Override
	public boolean estaParticipando(String correo, int identificador) throws ServiciosTreeCoreException {
		Boolean esta;
		try {
			esta = proyectoDAO.estaParticipando(correo, identificador);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
		return esta;
	}

	@Override
	public ArrayList<Rama> getRamas(int identificador) throws ServiciosTreeCoreException {
		ArrayList<Rama> ramas;
		try {
			ramas = proyectoDAO.getRamas(identificador);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
		return ramas;
	}
	
	@Override
	public Rama getSpecificProjectRama(int idProyecto, int idRama) throws ServiciosTreeCoreException{
		Rama rama=null;
		try {
			ArrayList<Rama> ramas= proyectoDAO.getRamas(idProyecto);
			int ramasSize=ramas.size();
			int cont=0;
			while(rama==null && cont<ramasSize) {
				Rama ramaLista=ramas.get(cont);
				if(ramaLista.getId()==idRama) {
					rama=ramaLista;
				}
				cont++;
			}
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("La rama no existe");
		}
		return rama;
	}

	@Override
	public void insertarProyecto(Proyecto proyecto) throws ServiciosTreeCoreException {
		try {
			proyectoDAO.insertarProyecto(proyecto);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
	}

	@Override
	public void insertarRama(Rama rama, Proyecto proyecto) throws ServiciosTreeCoreException {
		try {
			proyectoDAO.insertarRama(rama, proyecto);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
	}

	@Override
	public void insertarParticipante(Usuario usuario, Proyecto proyecto) throws ServiciosTreeCoreException {
		try {
			proyectoDAO.insertarParticipante(proyecto, usuario);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("No hay proyectos");
		}
	}

	@Override
	public void insertarMensaje(Mensaje mensaje, int proyecto) throws ServiciosTreeCoreException {
		try {
			proyectoDAO.insertarMensaje(mensaje, proyecto);
		} catch (PersistenceException e) {
			throw new ServiciosTreeCoreException("Error al ingresar mensaje");
		}

	}

}