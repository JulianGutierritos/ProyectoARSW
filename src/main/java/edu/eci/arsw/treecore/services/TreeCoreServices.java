package edu.eci.arsw.treecore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.treecore.persistence.TreeCorePersistence;

@Service
public class TreeCoreServices {

	@Autowired 
	private TreeCorePersistence treeCorePer;
	
	public boolean getConnection() {
        return treeCorePer.getConnection();
    }
}
