package edu.eci.arsw.treecore.persistence.impl;

import org.springframework.stereotype.Component;

import edu.eci.arsw.treecore.persistence.TreeCorePersistence;

@Component
public class DefaultTreeCorePersistence implements TreeCorePersistence{

	@Override
	public boolean getConnection() {
		return true;
	}

}
