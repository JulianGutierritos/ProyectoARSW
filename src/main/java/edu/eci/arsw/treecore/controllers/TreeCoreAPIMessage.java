package edu.eci.arsw.treecore.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Mensaje;
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
		try {
			treeCoreUserServices.addInvitacion(inv);
			msgt.convertAndSend("/project/user/invitacion." + correo, inv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}