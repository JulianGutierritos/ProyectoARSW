package edu.eci.arsw.treecore.exceptions;


public class ServiciosTreeCoreException extends Exception {

	private static final long serialVersionUID = 468187741104740422L;

	public ServiciosTreeCoreException(String msg) {
        super(msg);
    }
	
	public ServiciosTreeCoreException(String msg, Exception e) {
        super(msg,e);
    
    }
}