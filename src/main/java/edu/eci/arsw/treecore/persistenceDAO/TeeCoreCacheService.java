package edu.eci.arsw.treecore.persistenceDAO;

import java.util.ArrayList;

import edu.eci.arsw.treecore.model.impl.Invitacion;
import edu.eci.arsw.treecore.model.impl.Mensaje;
import edu.eci.arsw.treecore.model.impl.Notificacion;
import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;

public interface TeeCoreCacheService {
    
    public void insertarProyecto(Proyecto p);

    public void insertarProyectos(ArrayList <Proyecto> proyectos);

    public void insertarProyectosDeUsuario (ArrayList <Proyecto> proyectos, String usuario);

    public void insertarProyectoDeUsuario (Proyecto proyecto, String usuario);

    public void eliminarParticipante (String correo, int proyecto);

    public Proyecto getProyecto(int identificador);

    public ArrayList<Proyecto> getProyectosDeUsuario(String usuario);

    public void agregarMensaje(Mensaje mensaje, int proyecto);

    public void agregarRama (Rama rama, int proyecto);

    public void agregarParticipante (Usuario participante, int proyecto);

    public void eliminarRama (ArrayList<Rama> ramas, int proyecto);

    public void updateRama (Rama rama, int proyecto);

    public Usuario getUsuario (String correo);

    public void insertarUsuario (Usuario usuario);

    public void insertarUsuarios(ArrayList <Usuario> usuarios);

    public void insertarNotificacion (Notificacion notificacion, String usuario);

    public void insertarInvitacion (Invitacion invitacion, String usuario);

    public void eliminarInvitacion (ArrayList<Invitacion> invitaciones, String correo);

    public void eliminarProyecto (int proyectoId);
}