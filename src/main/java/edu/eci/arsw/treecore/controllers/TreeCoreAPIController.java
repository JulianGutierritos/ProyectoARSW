package edu.eci.arsw.treecore.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;
import edu.eci.arsw.treecore.services.TreeCoreStoreServices;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/treecore/v1")
public class TreeCoreAPIController {
	@Autowired
	TreeCoreUserServices treeCoreUserServices;
	@Autowired
	TreeCoreProjectServices treeCoreProjectServices;
	@Autowired
	TreeCoreStoreServices treeCoreStoreServices;

	@Autowired
	SimpMessagingTemplate msgt;

	/**
	 * Metodo que retorna todos los usuarios contenidos en la base de datos
	 * 
	 * @return Respuesta http con una lista de todos los usuarios
	 */
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<?> getAllUsers() {
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

	@RequestMapping(path = "/users/{correo}/notification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewProject(@RequestBody Notificacion notificacion,
			@PathVariable("correo") String correo) {
		try {
			this.treeCoreUserServices.insertarNotificacion(notificacion, correo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
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
			Subject subject = SecurityUtils.getSubject();
			user.setContraseña(new Sha256Hash(user.getPasswd()).toHex());
			UsernamePasswordToken token = new UsernamePasswordToken(user.getCorreo(), user.getPasswd());
			subject.login(token);

			// this.treeCoreUserServices.verificarCredenciales(user.getCorreo(),
			// user.getPasswd());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
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
			Proyecto p = treeCoreProjectServices.getProyectoByName(projectName);
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
	 * Metodo para crear una nueva rama
	 * 
	 * @param rama     rama a crear
	 * @param projetId Id del proyecto
	 * @return Respuesta http con una lista de las ramas pertenecientes a un
	 *         proyecto
	 */
	@RequestMapping(path = "/projects/{id}/ramas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewRoot(@RequestBody Rama rama, @PathVariable("id") int projectId) {
		try {
			Proyecto project = treeCoreProjectServices.getProyecto(projectId);
			int r = this.treeCoreProjectServices.insertarRama(rama, project);
			rama.setId(r);
			return new ResponseEntity<>(rama, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
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
	 * Metodo para eliminar un proyecto
	 * 
	 * @param project Proyecto a eliminar
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/delete/project", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteProject(@RequestBody Proyecto project) {
		try {
			this.treeCoreProjectServices.deleteProyecto(project);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * * Metodo que recibe la peticion para adicionar un nueva invitacion
	 * 
	 * @param invitacion Nueva invitacion
	 * @return Respuesta http con el estado de la solicitud
	 */
	@RequestMapping(path = "/user/invitation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewMessage(@RequestBody Invitacion invitacion) {
		try {
			this.treeCoreUserServices.addInvitacion(invitacion);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(path = "/delete/project/rama", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteRama(@RequestBody Rama rama) {
		try {
			System.out.println("lllllllllllllllllll");
			System.out.println(rama);
			treeCoreProjectServices.deleteRama(rama);
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
	 * 
	 * @param ruta ruta de la carpeta que se quiere consultar
	 * @return Respuesta http con el estado de la solicitud
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
	 * 
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
	 * 
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

	/**
	 * Metodo para subir un archivo a una ruta ya existente.
	 * 
	 * @param ruta Ruta a la que se quiere subir un archivo.
	 * @return
	 */
	@RequestMapping(path = "/file/{ruta}", method = RequestMethod.PUT)
	public ResponseEntity<?> putFile(@PathVariable("ruta") String ruta, @RequestParam("file") MultipartFile file) {
		try {
			treeCoreStoreServices.receiveFile(file, ruta, "put");
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (TreeCoreStoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Metodo para borrar un archivo.
	 * 
	 * @param ruta ruta a la que pertence un archivo.
	 * @return
	 */
	@RequestMapping(path = "/file/{ruta}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteFile(@PathVariable("ruta") String ruta) {
		try {
			treeCoreStoreServices.deleteFile(ruta);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (TreeCoreStoreException e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * Metodo para obtener el ultimo id disponible.
	 * 
	 * @return ultimo id disponible.
	 */
	@RequestMapping(path = "/ramas/lastId", method = RequestMethod.GET)
	public ResponseEntity<?> getLastBranchId() {
		try {
			return new ResponseEntity<>(treeCoreProjectServices.getLastBranchId(), HttpStatus.CREATED);
		} catch (Exception e) {
			Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
