package edu.eci.arsw.treecore.controllers;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;

@Controller
public class TreeCoreAPIMessage {
	@Autowired
	SimpMessagingTemplate msgt;

	@Autowired
	TreeCoreProjectServices treeCoreProjectServices;

	@Autowired
	TreeCoreUserServices treeCoreUserServices;

	/**
	 * Metodo para adicionar un nuevo mensaje al proyecto
	 * 
	 * @param men Nuevo mensaje
	 * @param idProyecto Id del proyecto
	 */
	@MessageMapping("/mensaje.{idProyecto}")
	public void handlerMessage(Mensaje men, @DestinationVariable String idProyecto) {
		Date currentDate = new Date();
		men.setFecha(currentDate);
		msgt.convertAndSend("/project/mensaje." + idProyecto, men);
		try {
			treeCoreProjectServices.insertarMensaje(men, Integer.parseInt(idProyecto));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para adicionar una nueva invitacion a un proyecto
	 * 
	 * @param inv Nuevo mensaje
	 * @param correo correo del usuario
	 */
	@MessageMapping("/invitacion.{correo}")
	public void handlerInvitation(Invitacion inv, @DestinationVariable String correo) {
		msgt.convertAndSend("/project/user/invitacion." + correo, inv);
	}

	/**
	 * Metodo para adicionar una nueva invitacion a un proyecto
	 * 
	 * @param inv Nuevo mensaje
	 * @param correo correo del usuario
	 */
	@MessageMapping("/notificacion.{correo}")
	public void handlerNotificacion(Notificacion not, @DestinationVariable String correo) {
		Date currentDate = new Date();
		not.setFecha(currentDate);
		msgt.convertAndSend("/project/user/notificacion." + correo, not);
	}
	
	/**
	 * Metodo para adicionar una nueva rama a un proyecto dado su id
	 * @param idProyecto Id del proyecto
	 * @param rama Nueva rama a adicionar
	 * @return Respuesta http con el estado de la solicitud
	 */
	@MessageMapping("/projects/{idProyecto}"+"/add/rama")
	public ResponseEntity<?> addProjectRama(@DestinationVariable String idProyecto, @RequestBody Rama rama) {
		try {
			int projectId=Integer.parseInt(idProyecto);
			Proyecto project = treeCoreProjectServices.getProyecto(projectId);
			Rama oldRama = treeCoreProjectServices.getSpecificProjectRama(projectId, rama.getId());

			if (oldRama == null) {
				this.treeCoreProjectServices.insertarRama(rama, project);
			} else {
				String ramaName = rama.getNombre();
				String ramaDescrip = rama.getDescripcion();
				oldRama.setNombre(ramaName);
				oldRama.setDescripcion(ramaDescrip);
				this.treeCoreProjectServices.updateRama(project.getId(), oldRama);
			}
			msgt.convertAndSend("/project/update/tree", rama);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * 
	 * @param root
	 * @throws ServiciosTreeCoreException
	 */
	@MessageMapping("/delRoot.{project}")
    public void handlerRootDelete(Rama root, @DestinationVariable int project) throws ServiciosTreeCoreException  {
		treeCoreProjectServices.deleteRama(root, project);
		msgt.convertAndSend("/project/del/tree." + project, root);
	}

	/**
	 * 
	 * @param root rama
	 * @throws ServiciosTreeCoreException
	 */
	@MessageMapping("/newRoot.{project}")
	public void updateTree(Rama root, @DestinationVariable int project) throws ServiciosTreeCoreException {
		msgt.convertAndSend("/project/add/tree." + project, root);
	}
	
	/**
	 * 
	 * @param project
	 * @throws ServiciosTreeCoreException
	 */
	@MessageMapping("/delProject")
    public void handlerProjectDelete(Proyecto project) throws ServiciosTreeCoreException  {
		msgt.convertAndSend("/project/delete." + project.getId(), project);
	}
	
	/**
	 * 
	 * @param root
	 * @param projectId
	 * @throws ServiciosTreeCoreException
	 */
	@MessageMapping("/updateRoot.{projectId}")
    public void handlerRootUpdate(Rama root, @DestinationVariable int projectId) throws ServiciosTreeCoreException  {
		this.treeCoreProjectServices.updateRama(projectId, root);
		msgt.convertAndSend("/project/update/root." + projectId, root);
	}

	@MessageMapping("/deleteUser.{correo}")
	public void handlerDeleteUser(Proyecto proyecto, @DestinationVariable String correo){
		msgt.convertAndSend("/project/delete/user." + proyecto.getId(), correo);
	}

	@MessageMapping("/aceptarInvitacion")
	public void handlerAceptarInvitacion(Invitacion invitacion) throws ServiciosTreeCoreException {
		Usuario u = treeCoreUserServices.getUsuario(invitacion.getReceptor());
		msgt.convertAndSend("/project/accept/user." + invitacion.getProyecto(), u);
	}
	
}