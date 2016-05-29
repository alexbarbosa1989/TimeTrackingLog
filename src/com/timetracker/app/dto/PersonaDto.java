package com.timetracker.app.dto;

import java.util.ArrayList;

public class PersonaDto {
	private String id_persona;
	private String nombres_persona;
	private String apellidos_persona;
	private ArrayList<ReporteDto> tareas;
	public String getId_persona() {
		return id_persona;
	}
	public void setId_persona(String id_persona) {
		this.id_persona = id_persona;
	}
	public String getNombres_persona() {
		return nombres_persona;
	}
	public void setNombres_persona(String nombres_persona) {
		this.nombres_persona = nombres_persona;
	}
	public String getApellidos_persona() {
		return apellidos_persona;
	}
	public void setApellidos_persona(String apellidos_persona) {
		this.apellidos_persona = apellidos_persona;
	}
	public ArrayList<ReporteDto> getTareas() {
		return tareas;
	}
	public void setTareas(ArrayList<ReporteDto> tareas) {
		this.tareas = tareas;
	}
	
}
