package edu.eci.arsw.treecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;

@RestController
@RequestMapping(value = "/treecore")
public class TreeCoreAPIController {
	
	@Autowired
    TreeCoreUserServices treeCoreServices;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getAllBlueprints()  {
		ResponseEntity r=null;
		try{
			boolean connect=true;
			if(connect)r = new ResponseEntity<>("Successful connection", HttpStatus.OK);
		} catch (Exception e){
			r = new ResponseEntity<>("Failure Connection", HttpStatus.NOT_FOUND);
		}
		return r;
	}
	
	@RequestMapping(value = "basicForm", method = RequestMethod.POST)
    public void basicForm(@RequestParam("id") int id, @RequestParam("name") String name,
    		@RequestParam("password") String pass, @RequestParam("email") String mail) {
		
		System.out.println(id);
	}
	

}
