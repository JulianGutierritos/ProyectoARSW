package edu.eci.arsw.treecore.model.impl;

import java.util.Date;

public class Mensaje {
	private int id; 
	private Usuario usuario;
	private Date fecha;
	private String contenido;

	public Mensaje(int id, Usuario usuario, Date fecha, String contenido) {
		this.usuario = usuario;
		this.fecha = fecha;
		this.contenido = contenido;
		this.id = id;
	}

	public Mensaje() {
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Mensaje{" + "usuario=" + usuario + ", fecha=" + fecha + ", contenido=" + contenido + "}";
	}

}