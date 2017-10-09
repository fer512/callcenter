package com.almundo.model;

public abstract class Empleado implements IEmpleado {

	private Boolean disponible = true;
	
	private String nombre;
	
	public Boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
}
