package edu.eci.arsw.treecore.persistence.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import edu.eci.arsw.treecore.model.impl.Proyecto;

public interface ProyectoMapper {

    public ArrayList<Proyecto> getProyectos();

    public Proyecto getProyecto(@Param("id") int id);
    
    public ArrayList<Proyecto> getProyectosUsuario(@Param("correo") String correo);
}