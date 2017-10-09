package com.almundo.model;

import com.almundo.util.GeneradorId;
import com.almundo.util.LlamadaEstados;

public class Llamada implements ILlamada {
	Integer id;
	LlamadaEstados estado;
	
	public Integer getId() {
		return id;
	}

	public Llamada() {
		super();
		this.id = GeneradorId.id++;
		this.estado = LlamadaEstados.PENDIENTE;
	}

	public LlamadaEstados getEstado() {
		return estado;
	}

	public void setEstado(LlamadaEstados estado) {
		this.estado = estado;
	}

	
}
