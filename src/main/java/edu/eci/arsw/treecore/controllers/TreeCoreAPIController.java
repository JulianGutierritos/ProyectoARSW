package edu.eci.arsw.treecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import edu.eci.arsw.treecore.exceptions.ProjectNotFoundException;
import edu.eci.arsw.treecore.services.TreeCoreServices;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/treecore")
public class TreeCoreAPIController {
	
	@Autowired
	TreeCoreServices treeCoreServices;
	

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> GetAllProyects(){
        try {
            return new ResponseEntity<>(treeCoreServices.getAllProyectos(),HttpStatus.ACCEPTED);
        } catch (ProjectNotFoundException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
	}
	
	@RequestMapping(path ="/{creator}",method = RequestMethod.GET)
    public ResponseEntity<?> GetProjectsOfUser(@PathVariable ("creator") String creatorName){
        try {
            return new ResponseEntity<>(treeCoreServices.getAllProyectosUser(),HttpStatus.ACCEPTED);
        } catch (ProjectNotFoundException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

	@RequestMapping(path ="/{creator}/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> GetProjectByCreatorAndName(@PathVariable ("creator") String creatorName, @PathVariable ("name") String projectName){
        try {
            return new ResponseEntity<>(treeCoreServices.getProyecto(creatorName,projectName),HttpStatus.ACCEPTED);
        } catch (ProjectNotFoundException e) {
            Logger.getLogger(TreeCoreAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
	}
	

}
