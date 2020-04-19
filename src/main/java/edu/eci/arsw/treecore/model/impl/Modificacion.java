package edu.eci.arsw.treecore.model.impl;

import java.io.File;
import java.util.Date;

public class Modificacion {
	private File archivo;
	private Date fecha;
	private Usuario modificadoPor;

	public Modificacion(File archivo, Date fecha, Usuario modificadoPor) {
		this.archivo = archivo;
		this.fecha = fecha;
		this.modificadoPor = modificadoPor;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Usuario modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
}