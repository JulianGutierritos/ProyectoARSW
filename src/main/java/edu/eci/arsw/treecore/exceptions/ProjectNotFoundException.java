package edu.eci.arsw.treecore.exceptions;


public class ProjectNotFoundException extends Exception {

    private static final long serialVersionUID = 468187741104740422L;

    public ProjectNotFoundException(String msg) {
        super(msg);
    }

    public ProjectNotFoundException(String msg, Exception e) {
        super(msg,e);
    
    }
}