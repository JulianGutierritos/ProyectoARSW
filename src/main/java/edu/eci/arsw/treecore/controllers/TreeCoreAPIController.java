package edu.eci.arsw.treecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;
import edu.eci.arsw.treecore.services.TreeCoreStoreServices;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/treecore")
public class TreeCoreAPIController {
	@Autowired
	TreeCoreUserServices treeCoreUserServices;
	@Autowired
	TreeCoreProjectServices treeCoreProjectServices;
	@Autowired
	TreeCoreStoreServices treeCoreStoreServices;

	/**
	 * Metodo que retorna todos los usuarios contenidos en la base de datos
	 * 
	 * @return Respuesta http con una lista de todos los usuarios
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		try {
			return new ResponseEntity<>(this.treeCoreUserServices.getAllUsers(), HttpStatus.OK);
		} catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna un usuario dado su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Respuesta http con el usuario correspondiente
	 */
	@RequestMapping(path = "/users/{correo}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("correo") String correo) {
		try {
			return new ResponseEntity<>(treeCoreUserServices.getUsuario(correo), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna todos los proyectos de un usuario dado su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Respuesta http con una lista de todos los proyecos pertenecientes a
	 *         ese usuario
	 */
	@RequestMapping(path = "/users/{correo}/projects", method = RequestMethod.GET)
	public ResponseEntity<?> getUserProjects(@PathVariable("correo") String correo) {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getAllProyectosUser(correo), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna todas las notificaciones de un usuario dado su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Respuesta http de todos las notificaciones pertenecientes a ese
	 *         usuario
	 */
	@RequestMapping(path = "/users/{correo}/notifications", method = RequestMethod.GET)
	public ResponseEntity<?> getUserNotificatios(@PathVariable("correo") String correo) {
		try {
			return new ResponseEntity<>(treeCoreUserServices.getNotificaciones(correo), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna todas las invitaciones de un usuario dado su correo
	 * 
	 * @param correo Correo del usuario
	 * @return Respuesta http de todos las invitaciones pertenecientes a ese usuario
	 */
	@RequestMapping(path = "/users/{correo}/invitations", method = RequestMethod.GET)
	public ResponseEntity<?> getUserInvitations(@PathVariable("correo") String correo) {
		try {
			return new ResponseEntity<>(treeCoreUserServices.getInvitaciones(correo), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que recibe la peticion de loggeo un usuario
	 * 
	 * @param user Usuario
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody Usuario user) {
		try {
			this.treeCoreUserServices.verificarCredenciales(user.getCorreo(), user.getPasswd());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que recibe la peticion para adicionar un nuevo usuario
	 * 
	 * @param user Nuevo usuario
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewUser(@RequestBody Usuario user) {
		try {
			this.treeCoreUserServices.addNewUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * Metodo que retorna todos los proyectos almacenados en la base de datos
	 * 
	 * @return Lista de proyectos
	 */
	@RequestMapping(path = "/projects", method = RequestMethod.GET)
	public ResponseEntity<?> getAllProyects() {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getAllProyectos(), HttpStatus.OK);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna un proyecto dado su id
	 * 
	 * @param id Id del proyecto concultado
	 * @return Respuesta http con el proyecto solicitado
	 */
	@RequestMapping(path = "/projects/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProject(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getProyecto(id), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna un proyecto dado su nombre
	 * 
	 * @param projectName Nombre del proyecto
	 * @return Respuesta http con el proyecto solicitado
	 */
	@RequestMapping(path = "/project/{projectName}", method = RequestMethod.GET)
	public ResponseEntity<?> getProjectByName(@PathVariable("projectName") String projectName) {
		try {
			System.out.println(projectName);

			Proyecto p = treeCoreProjectServices.getProyectoByName(projectName);
			System.out.println(p);
			return new ResponseEntity<>(treeCoreProjectServices.getProyectoByName(projectName), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna todas las ramas de un proyecto dado su id
	 * 
	 * @param id Id del proyecto
	 * @return Respuesta http con una lista de las ramas pertenecientes a un
	 *         proyecto
	 */
	@RequestMapping(path = "/projects/{id}/ramas", method = RequestMethod.GET)
	public ResponseEntity<?> getProjectRamas(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getRamas(id), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para obtener una rama en particular perteneciente a un proyecto
	 * 
	 * @param projectId Id del proyecto
	 * @param ramaId    Id de la rama
	 * @return Respuesta http con la rama según su id y id del proyecto solicitados
	 */
	@RequestMapping(path = "/projects/{projectId}/rama/{ramaId}", method = RequestMethod.GET)
	public ResponseEntity<?> getSpecificProjectRama(@PathVariable("projectId") int projectId,
			@PathVariable("ramaId") int ramaId) {

		try {
			return new ResponseEntity<>(treeCoreProjectServices.getSpecificProjectRama(projectId, ramaId),
					HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * * Metodo que recibe la peticion para adicionar un nuevo proyecto
	 * 
	 * @param project Nuevo proyecto
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/projects", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewProject(@RequestBody Proyecto project) {
		try {
			this.treeCoreProjectServices.insertarProyecto(project);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * Metodo para adicionar una nueva rama a un proyecto dado su id
	 * 
	 * @param projectId Id del proyecto
	 * @param rama      Nueva rama a adicionar
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/projects/{projectId}/ramas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProjectRama(@PathVariable("projectId") int projectId, @RequestBody Rama rama) {
		try {
			Proyecto project = treeCoreProjectServices.getProyecto(projectId);
			Rama oldRama = treeCoreProjectServices.getSpecificProjectRama(projectId, rama.getId());

			if (oldRama == null) {
				this.treeCoreProjectServices.insertarRama(rama, project);
			} else {
				String ramaName = rama.getNombre();
				String ramaDescrip = rama.getDescripcion();
				oldRama.setNombre(ramaName);
				oldRama.setDescripcion(ramaDescrip);
				this.treeCoreProjectServices.updateRama(project, oldRama);
			}
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@RequestMapping(path = "/delete/project/rama", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteRama(@RequestBody Rama rama) {
		try {
			treeCoreProjectServices.deleteRama(rama);
			System.out.println(rama.getId());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna todos los mensajes pertenecientes a un proyecto dado su id
	 * 
	 * @param id Id del prouecto
	 * @return Respuesta http con una lista de los mensajes pertenecientes a un
	 *         proyecto
	 */
	@RequestMapping(path = "/projects/{id}/messages", method = RequestMethod.GET)
	public ResponseEntity<?> getMessages(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getMensajes(id), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para eliminar una invitacion
	 * 
	 * @param invitacion Invitacion a eliminar
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/users/invitations", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteInvitation(@RequestBody Invitacion invitacion) {
		try {
			treeCoreUserServices.deleteInvitacion(invitacion);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que retorna un equipo dado su id
	 * 
	 * @param id Id del equipo
	 * @return Respuesta http con el equipo solicitado
	 */
	@RequestMapping(path = "/projects/{id}/team", method = RequestMethod.GET)
	public ResponseEntity<?> getTeam(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<>(treeCoreProjectServices.getParticipantes(id), HttpStatus.ACCEPTED);
		} catch (ServiciosTreeCoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para agregar un nuevo integrante al equipo del proyecto
	 * 
	 * @param invitacion Inivitación del nuevo integrante
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/projects/team", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTeamMate(@RequestBody Invitacion invitacion) {
		try {
			Usuario usuario = treeCoreUserServices.getUsuario(invitacion.getReceptor());
			Proyecto project = treeCoreProjectServices.getProyecto(invitacion.getProyecto());
			this.treeCoreProjectServices.insertarParticipante(usuario, project);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Metodo para consultar los archivos y carpetas en una ruta.
	 * @param ruta ruta de la carpeta que se quiere consultar
	 * @return	Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/files/{ruta}", method = RequestMethod.GET)
	public ResponseEntity<?> getFiles(@PathVariable("ruta") String ruta) {
		try {
			return new ResponseEntity<>(treeCoreStoreServices.consultar(ruta), HttpStatus.ACCEPTED);
		} catch (TreeCoreStoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para obtener un link de descarga de un archivo.
	 * @param ruta ruta del archivo
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/file/{ruta}", method = RequestMethod.GET)
	public ResponseEntity<?> getFile(@PathVariable("ruta") String ruta) {
		try {
			return new ResponseEntity<>(treeCoreStoreServices.downloadFile(ruta), HttpStatus.ACCEPTED);
		} catch (TreeCoreStoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo para subir un archivo a una ruta.
	 * @param ruta ruta a la que se quiere subir un archivo.
	 * @return
	 */
	@RequestMapping(path = "/file/{ruta}", method = RequestMethod.POST)
	public ResponseEntity<?> postFile(@PathVariable("ruta") String ruta, @RequestParam("file") MultipartFile file) {
		try {
			treeCoreStoreServices.receiveFile(file, ruta, "post");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (TreeCoreStoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
