package edu.eci.arsw.treecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import edu.eci.arsw.treecore.services.TreeCoreServices;

@RestController
@RequestMapping(value = "/treecore")
public class TreeCoreAPIController {
	
	@Autowired
    TreeCoreServices treeCoreServices;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getAllBlueprints()  {
		ResponseEntity r=null;
		try{
			boolean connect=this.treeCoreServices.getConnection();
			if(connect)r = new ResponseEntity<>("Successful connection", HttpStatus.OK);
		} catch (Exception e){
			r = new ResponseEntity<>("Failure Connection", HttpStatus.NOT_FOUND);
		}
		return r;
	}

}
