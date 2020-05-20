package edu.eci.arsw.treecore.persistenceDAO.impl;

import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.persistenceDAO.TeeCoreCacheService;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class TreeCoreCacheServiceImpl implements TeeCoreCacheService {

	HashMap<Integer, Proyecto> proyectos = new HashMap<Integer, Proyecto>();
	HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
	HashMap<String, ArrayList<Proyecto>> proyectosDeUsuario = new HashMap<String, ArrayList<Proyecto>>();

	@Override
	public void insertarProyecto(Proyecto p) {
		synchronized (proyectos) {
			proyectos.put(p.getId(), p);
		}
	}

	@Override
	public void insertarProyectos(ArrayList<Proyecto> proyectos) {
		synchronized (this.proyectos) {
			for (int i = 0; i < proyectos.size(); i++) {
				this.proyectos.put(proyectos.get(i).getId(), proyectos.get(i));
			}
		}
	}

	@Override
	public void insertarProyectosDeUsuario(ArrayList<Proyecto> proyectos, String usuario) {
		synchronized (this.proyectosDeUsuario) {
			this.proyectosDeUsuario.put(usuario, proyectos);
		}
		synchronized (this.proyectos) {
			for (int i = 0; i < proyectos.size(); i++) {
				this.proyectos.put(proyectos.get(i).getId(), proyectos.get(i));
			}
		}
	}

	@Override
	public void insertarProyectoDeUsuario(Proyecto proyecto, String usuario) {
		synchronized (proyectosDeUsuario) {
			if (proyectosDeUsuario.get(usuario) != null) {
				proyectosDeUsuario.get(usuario).add(proyecto);
			}
		}
	}

	@Override
	public Proyecto getProyecto(int identificador) {
		synchronized (proyectos) {
			return proyectos.get(identificador);
		}
	}

	@Override
	public ArrayList<Proyecto> getProyectosDeUsuario(String usuario) {
		synchronized (proyectosDeUsuario) {
			return proyectosDeUsuario.get(usuario);
		}
	}

	@Override
	public void agregarMensaje(Mensaje mensaje, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				proyectos.get(proyecto).addMensaje(mensaje);
			}
		}
	}

	@Override
	public void agregarRama(Rama rama, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				proyectos.get(proyecto).addRama(rama);
			}
		}
	}

	@Override
	public void agregarParticipante(Usuario participante, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				proyectos.get(proyecto).addParticipante(participante);
			}
		}
		synchronized (proyectosDeUsuario) {
			if (proyectosDeUsuario.get(participante.getCorreo()) != null) {
				proyectosDeUsuario.get(participante.getCorreo()).add(proyectos.get(proyecto));
			}
		}
	}

	public void eliminarParticipante(String correo, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				proyectos.get(proyecto).eliminarParticipante(correo);
			}
		}
		synchronized (proyectosDeUsuario) {
			if (proyectosDeUsuario.get(correo) != null) {
				for (int i = 0; i < proyectosDeUsuario.get(correo).size(); i++) {
					if (proyectosDeUsuario.get(correo).get(i).getId() == proyecto) {
						proyectosDeUsuario.get(correo).remove(i);
						break;
					}
				}
			}
		}

	}

	@Override
	public void eliminarRama(Rama rama, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				int pos = -1;
				for (int i = 0; i < proyectos.get(proyecto).getRamas().size(); i++) {
					if (proyectos.get(proyecto).getRamas().get(i).getId() == rama.getId()) {
						pos = i;
					} else {
						if (proyectos.get(proyecto).getRamas().get(i).getRamaPadre() != null) {
							if (proyectos.get(proyecto).getRamas().get(i).getRamaPadre().getId() == rama.getId()) {
								proyectos.get(proyecto).getRamas().get(i).setRamaPadre(rama.getRamaPadre());
							}
						}
					}
				}
				;
				if (pos >= 0) {
					proyectos.get(proyecto).getRamas().remove(pos);
				}
			}
		}
	}

	@Override
	public void updateRama(Rama rama, int proyecto) {
		synchronized (proyectos) {
			if (proyectos.get(proyecto) != null) {
				ArrayList<Rama> ramas = proyectos.get(proyecto).getRamas();
				for (int i = 0; i < ramas.size(); i++) {
					if (ramas.get(i).getId() == rama.getId()) {
						ramas.set(i, rama);
						break;
					}
				}
				proyectos.get(proyecto).setRamas(ramas);
			}
		}
	}

	@Override
	public Usuario getUsuario(String correo) {
		synchronized (usuarios) {
			return usuarios.get(correo);
		}
	}

	@Override
	public void insertarUsuario(Usuario usuario) {
		synchronized (usuarios) {
			usuarios.put(usuario.getCorreo(), usuario);
		}
	}

	@Override
	public void insertarUsuarios(ArrayList<Usuario> usuarios) {
		synchronized (this.usuarios) {
			for (int i = 0; i < usuarios.size(); i++) {
				this.usuarios.put(usuarios.get(i).getCorreo(), usuarios.get(i));
			}
		}
	}

	@Override
	public void insertarNotificacion(Notificacion notificacion, String usuario) {
		synchronized (this.usuarios) {
			if (usuarios.get(usuario) != null) {
				usuarios.get(usuario).addNotificacion(notificacion);
			}
		}
	}

	@Override
	public void insertarInvitacion(Invitacion invitacion, String usuario) {
		synchronized (this.usuarios) {
			if (usuarios.get(usuario) != null) {
				usuarios.get(usuario).addInvitacion(invitacion);
			}
		}
	}

	@Override
	public void eliminarInvitacion(Invitacion invitacion, String correo) {
		synchronized (usuarios) {
			if (usuarios.get(correo) != null) {
				for (int i = 0; i < usuarios.get(correo).getInvitaciones().size(); i++) {
					if (usuarios.get(correo).getInvitaciones().get(i).getProyecto() == invitacion.getProyecto()) {
						usuarios.get(correo).getInvitaciones().remove(i);
						break;
					}
				}
			}
		}
	}

	public void eliminarProyecto(int proyectoId) {
		Proyecto proyecto = proyectos.get(proyectoId);
		if (proyecto != null) {
			synchronized (proyectosDeUsuario) {
				String correo;
				for (int i = 0; i < proyecto.getParticipantes().size(); i++) {
					correo = proyecto.getParticipantes().get(i).getCorreo();
					if (proyectosDeUsuario.get(correo) != null) {
						for (int j = 0; j < proyectosDeUsuario.get(correo).size(); j++) {
							if (proyectosDeUsuario.get(correo).get(j).getId() == proyecto.getId()) {
								proyectosDeUsuario.get(correo).remove(j);
								break;
							}
						}
					}
				}
			}
			synchronized (this.proyectos) {
				this.proyectos.remove(proyecto.getId());
			}
		}
	}
}