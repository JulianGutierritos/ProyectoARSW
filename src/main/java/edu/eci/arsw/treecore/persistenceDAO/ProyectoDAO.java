package edu.eci.arsw.treecore.persistenceDAO;

import java.util.ArrayList;

import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.exceptions.PersistenceException;

public interface ProyectoDAO {
    
    public Proyecto getProyecto(int identificador) throws PersistenceException;
	public ArrayList<Proyecto> getProyectos() throws PersistenceException;
	public ArrayList<Proyecto> getProyectosUsuario(String correo) throws PersistenceException;
	public ArrayList<Usuario> getParticipantes(int identificador) throws PersistenceException;
	public boolean estaParticipando (String correo, int identificador);
	public ArrayList<Rama> getRamas (int identificador);
	public ArrayList<Mensaje> getMensajes (int identificador);
	
}
