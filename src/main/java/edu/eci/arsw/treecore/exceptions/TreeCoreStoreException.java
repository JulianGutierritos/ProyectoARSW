package edu.eci.arsw.treecore.exceptions;

public class TreeCoreStoreException extends Exception {
   
    private static final long serialVersionUID = -9057269393156569536L;

    public TreeCoreStoreException(String msg) {
        super(msg);
    }
	
	public TreeCoreStoreException(String msg, Exception e) {
        super(msg,e);
    
    }
}