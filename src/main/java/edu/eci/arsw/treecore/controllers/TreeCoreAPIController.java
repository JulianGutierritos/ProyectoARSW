package edu.eci.arsw.treecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreProjectServices;
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

    
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        try {
        	return new ResponseEntity<>(this.treeCoreUserServices.getAllUsers(), HttpStatus.OK);
		} 
        catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }

    
    /**
     * 
     * @return
     */
    @RequestMapping(path = "/projects", method = RequestMethod.GET)
    public ResponseEntity<?> GetAllProyects() {
        try {
            return new ResponseEntity<>(treeCoreProjectServices.getAllProyectos(), HttpStatus.OK);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    
    /**
     * 
     * @param creatorName
     * @return
     */
    @RequestMapping(path = "/users/{correo}/projects", method = RequestMethod.GET)
    public ResponseEntity<?> GetUserProjects(@PathVariable("correo") String correo) {
        try {
            return new ResponseEntity<>(treeCoreProjectServices.getAllProyectosUser(correo), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> GetProject(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(treeCoreProjectServices.getProyecto(id), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/projects/{id}/ramas", method = RequestMethod.GET)
    public ResponseEntity<?> GetRamas(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(treeCoreProjectServices.getRamas(id), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/projects/{id}/team", method = RequestMethod.GET)
    public ResponseEntity<?> GetTeam(@PathVariable("id") int id) {

        try {
            return new ResponseEntity<>(treeCoreProjectServices.getParticipantes(id), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "/projects/{id}/menssages", method = RequestMethod.GET)
    public ResponseEntity<?> GetMessages(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(treeCoreProjectServices.getMensajes(id), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    
    /**
     * 
     * @param correo
     * @return
     */
    @RequestMapping(path = "/users/{correo}", method = RequestMethod.GET)
    public ResponseEntity<?> GetUser(@PathVariable("correo") String correo) {
        try {
            return new ResponseEntity<>(treeCoreUserServices.getUsuario(correo), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param correo
     * @return
     */
    @RequestMapping(path = "/users/{correo}/invitations", method = RequestMethod.GET)
    public ResponseEntity<?> GetUserInvitations(@PathVariable("correo") String correo) {
        try {
            return new ResponseEntity<>(treeCoreUserServices.getInvitaciones(correo), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * 
     * @param correo
     * @return
     */
    @RequestMapping(path = "/users/{correo}/notifications", method = RequestMethod.GET)
    public ResponseEntity<?> GetUserNotificatios(@PathVariable("correo") String correo) {
        try {
            return new ResponseEntity<>(treeCoreUserServices.getNotificaciones(correo), HttpStatus.ACCEPTED);
        } catch (ServiciosTreeCoreException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	
    
    /**
	 * Metodo que recibe la peticion de loggeo un usuario
	 * @param user Usuario
	 */
    @RequestMapping(path = "/login", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody Usuario user){
    	try {
			this.treeCoreUserServices.verificarCredenciales(user.getCorreo(), user.getPasswd());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	} 
    	catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    
    
    /**
	 * Metodo que recibe la peticion para adicionar un nuevo usuario
	 * @param user Nuevo Usuario
	 */
    @RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody Usuario user){
    	try {
			this.treeCoreUserServices.addNewUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
    	} 
    	catch (ServiciosTreeCoreException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
    	
	}
    
    
    /**
	 * Metodo que recibe la peticion para adicionar un nuevo proyecto
	 * @param project Nuevo proyecto
	 */
    @RequestMapping(path = "/projects", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewProject(@RequestBody Proyecto project){
    	try {
            System.out.println(project.getCreador());
			this.treeCoreProjectServices.insertarProyecto(project);
			return new ResponseEntity<>(HttpStatus.CREATED);
    	} 
    	catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
    	
	}
    
    
}
