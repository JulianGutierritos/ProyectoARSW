package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.persistence.mappers.ProyectoMapper;
import edu.eci.arsw.treecore.persistenceDAO.ProyectoDAO;
import edu.eci.arsw.treecore.persistenceDAO.TeeCoreCacheService;
import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;

import java.util.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBatisProyectoDAO implements ProyectoDAO {

	@Autowired
	private ProyectoMapper proyectoMapper;

	@Autowired
	private TeeCoreCacheService treeCoreCacheService;

	@Override
	public Proyecto getProyecto(int identificador) throws PersistenceException {
		Proyecto proyecto = treeCoreCacheService.getProyecto(identificador);
		if (proyecto != null){
			return proyecto;
		}
		else{
			proyecto = proyectoMapper.getProyecto(identificador);
			if (proyecto == null)
				throw new PersistenceException("Proyecto no encontrado");
			else{
				treeCoreCacheService.insertarProyecto(proyecto);
				return proyecto;
			}
		}
	}

	@Override
	public Proyecto getProyectoByName(String projectName) throws PersistenceException {
		Proyecto proyecto = proyectoMapper.getProyectoByName(projectName);
		if (proyecto == null)
			throw new PersistenceException("Proyecto no encontrado");
		else
			return proyecto;
	}

	@Override
	public ArrayList<Proyecto> getProyectos() throws PersistenceException {
		ArrayList<Proyecto> projects = this.proyectoMapper.getProyectos();
		if (projects == null)
			throw new PersistenceException("Proyectos no encontrados");
		else{
			treeCoreCacheService.insertarProyectos(projects);
			return projects;
		}
	}

	@Override
	public ArrayList<Proyecto> getProyectosUsuario(String correo) throws PersistenceException {
		if (treeCoreCacheService.getProyectosDeUsuario(correo) != null){
			return treeCoreCacheService.getProyectosDeUsuario(correo);
		}
		else{
			ArrayList<Proyecto> proyectos = proyectoMapper.getProyectosUsuario(correo);
			if (proyectos == null)
				throw new PersistenceException("El usuario no tiene proyectos");
			else{
				treeCoreCacheService.insertarProyectosDeUsuario(proyectos, correo);
				return proyectos;
			}
		}		
	}

	@Override
	public ArrayList<Usuario> getParticipantes(int identificador) throws PersistenceException {
		Proyecto proyecto = treeCoreCacheService.getProyecto(identificador);
		if (proyecto != null){
			return proyecto.getParticipantes();
		}
		else{
			proyecto = proyectoMapper.getProyecto(identificador);
			if (proyecto == null)
				throw new PersistenceException("Proyecto no encontrado");
			else{
				treeCoreCacheService.insertarProyecto(proyecto);
				return proyecto.getParticipantes();
			}
		}	
	}

	@Override
	public boolean estaParticipando(String correo, int identificador) throws PersistenceException {
		try {
			ArrayList<Usuario> participantes = getParticipantes(identificador);
			boolean esta = false;
			for (int i = 0; i < participantes.size(); i++) {
				if (correo.equals(participantes.get(i).getCorreo())) {
					esta = true;
					break;
				}
			}
			return esta;
		} catch (PersistenceException e) {
			throw (e);
		}
	}

	@Override
	public ArrayList<Rama> getRamas(int identificador) throws PersistenceException {
		Proyecto proyecto = treeCoreCacheService.getProyecto(identificador);
		if (proyecto != null){
			return proyecto.getRamas();
		}
		else{
			proyecto = proyectoMapper.getProyecto(identificador);
			if (proyecto == null)
				throw new PersistenceException("Proyecto no encontrado");
			else{
				treeCoreCacheService.insertarProyecto(proyecto);
				return proyecto.getRamas();
			}
		}
	}

	@Override
	public ArrayList<Mensaje> getMensajes(int identificador) throws PersistenceException {
		Proyecto proyecto = treeCoreCacheService.getProyecto(identificador);
		if (proyecto != null){
			return proyecto.getMensajes();
		}
		else{
			proyecto = proyectoMapper.getProyecto(identificador);
			if (proyecto == null)
				throw new PersistenceException("Proyecto no encontrado");
			else{
				treeCoreCacheService.insertarProyecto(proyecto);
				return proyecto.getMensajes();
			}
		}
	}

	@Override
	public void insertarProyecto(Proyecto proyecto) throws PersistenceException {
		try {
			int i = proyectoMapper.insertarProyecto(proyecto);
			Proyecto p = getProyecto(i);
			treeCoreCacheService.insertarProyectoDeUsuario(p, proyecto.getCreador().getCorreo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new PersistenceException("Error al insertar proyecto");
		}
	}

	@Override
	public void insertarParticipante(Proyecto proyecto, Usuario usuario) throws PersistenceException {
		try {
			proyectoMapper.insertarParticipante(usuario, proyecto);
			treeCoreCacheService.agregarParticipante(usuario, proyecto.getId());
		} catch (Exception e) {
			throw new PersistenceException("Error al eliminar participante");
		}
	}

	@Override
	public void eliminarParticipante(String correo, Proyecto proyecto) throws PersistenceException{
		try {
			proyectoMapper.eliminarParticipante(correo, proyecto);
			treeCoreCacheService.eliminarParticipante(correo, proyecto.getId());
			if (getProyecto(proyecto.getId()).getParticipantes().size() == 0){
				deleteProyecto(proyecto);
			}
		} catch (Exception e) {
			throw new PersistenceException("Error al insertar participante");
		}
	}

	@Override
	public int insertarRama(Rama rama, Proyecto proyecto) throws PersistenceException {
		try {
			int r = proyectoMapper.insertarRamaConPadre(rama, proyecto);
			rama.setId(r);
			Date currentDate = new Date();
			rama.setFechaDeCreacion(currentDate);
			treeCoreCacheService.agregarRama(rama, proyecto.getId());
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException("Error al insertar rama");
		}
	}

	@Override
	public void deleteRama(Rama rama, int proyecto) throws PersistenceException {
		try {
			proyectoMapper.delRama(rama);
			treeCoreCacheService.eliminarRama(proyectoMapper.getProyectosRamas(proyecto), proyecto);
		} catch (Exception e) {
			throw new PersistenceException("No se ha podido eliminar la rama");
		}
	}

	@Override
	public void deleteProyecto(Proyecto project) throws PersistenceException {
		try {
			proyectoMapper.delProyecto(project);
			treeCoreCacheService.eliminarProyecto(project.getId());
		} catch (Exception e) {
			throw new PersistenceException("No se ha podido eliminar el proyecto");
		}
	}

	@Override
	public void insertarMensaje(Mensaje mensaje, int proyecto) throws PersistenceException {
		try {
			int i =proyectoMapper.insertarMensaje(mensaje, proyecto);
			mensaje.setId(i);
			Date currentDate = new Date();
			mensaje.setFecha(currentDate);
			treeCoreCacheService.agregarMensaje(mensaje, proyecto);
		} catch (Exception e) {
			throw new PersistenceException("Error al insertar mensaje");
		}
	}

	@Override
	public void updateRama(int proyectoId, Rama rama) throws PersistenceException {
		try {
			proyectoMapper.updateRama(proyectoId, rama);
			treeCoreCacheService.updateRama(rama, proyectoId);
		} catch (Exception e) {
			throw new PersistenceException("Error al actualizar la rama");
		}

	}

	@Override
	public String getAccessToken(int tokenId) throws PersistenceException {
		try {
			String token = proyectoMapper.getAccessToken(0);
			return token;
		} catch (Exception e) {
			throw new PersistenceException("Error consiguiendo el token");
		}
	}

	@Override
	public int getLastBranchId() {
		return proyectoMapper.getLastBranchId();
	}

}
