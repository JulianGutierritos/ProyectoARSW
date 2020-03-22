package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;
import edu.eci.arsw.treecore.model.impl.Usuario;

public interface ProyectoMapper {

    public ArrayList<Proyecto> getProyectos();

    public Proyecto getProyecto(@Param("id") int id);
    
    public ArrayList<Proyecto> getProyectosUsuario(@Param("correo") String correo);

    public void insertarProyecto(@Param("proyecto") Proyecto proyecto);

    public void insertarParticipante(@Param("usuario") Usuario usuario, @Param("proyecto") Proyecto proyecto);
    
    public void insertarRamaConPadre(@Param("rama") Rama rama, @Param("proyecto") Proyecto proyecto);

    public void insertarRama(@Param("rama") Rama rama, @Param("proyecto") Proyecto proyecto);


}