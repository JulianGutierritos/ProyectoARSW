package edu.eci.arsw.treecore.model.impl;

import java.io.File;
import java.util.Date;
public class Archivo {
    private File archivo;
    private Date ultimaModificacion;
    private Usuario modificadoPor;
    public Archivo(File archivo, Date ultimaModificacion, Usuario modificadoPor){
        this.archivo = archivo;
        this.ultimaModificacion = ultimaModificacion;
        this.modificadoPor = modificadoPor;
    }
    public File getArchivo(){
        return archivo;
    }
    public void setArchivo(File archivo){
        this.archivo = archivo;
    }
    public Date getUltimaModificacion(){
        return ultimaModificacion;
    }
    public void seetUltimaModificacion(Date ultimaModificacion){
        this.ultimaModificacion = ultimaModificacion;
    }
    public Usuario getModificadoPor(){
        return modificadoPor;
    }
    public void setModificadoPor(Usuario modificadoPor){
        this.modificadoPor = modificadoPor;
    }
}
