package edu.eci.arsw.treecore.exceptions;


public class PersistenceException extends Exception {

	private static final long serialVersionUID = 468187741104740422L;

	public PersistenceException(String msg) {
        super(msg);
    }
	
	public PersistenceException(String msg, Exception e) {
        super(msg,e);
    
    }
}