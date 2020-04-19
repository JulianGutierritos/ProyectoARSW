package edu.eci.arsw.treecore.model.impl;

import java.util.ArrayList;

public class Archivo {
	private ArrayList<Modificacion> modificaciones;

	public Archivo(ArrayList<Modificacion> modificaciones) {
		this.modificaciones = modificaciones;
	}

	public ArrayList<Modificacion> getModificaciones() {
		return modificaciones;
	}

	public void setModificaciones(ArrayList<Modificacion> modificaciones) {
		this.modificaciones = modificaciones;
	}
}
