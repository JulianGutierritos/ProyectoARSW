package edu.eci.arsw.treecore.model.impl;

import java.util.Date;

public class Notificacion {
	private int id;
	private Date fecha;
	private String informacion;

	public Notificacion(int id, Date fecha, String informacion) {
		this.id = id;
		this.fecha = fecha;
		this.informacion = informacion;
	}

	public Notificacion() {

	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}