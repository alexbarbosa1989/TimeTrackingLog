package com.timetracker.app.dto;


public class ReporteDto {
	private int id_tarea;
	private String nombre_tarea;
	private String fecha_inicio;
	private String fecha_fin;
	private String urgente;
	private String importante;
	
	
	public int getId_tarea() {
		return id_tarea;
	}
	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}
	public String getNombre_tarea() {
		return nombre_tarea;
	}
	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getUrgente() {
		return urgente;
	}
	public void setUrgente(String urgente) {
		this.urgente = urgente;
	}
	public String getImportante() {
		return importante;
	}
	public void setImportante(String importante) {
		this.importante = importante;
	}
	

}
