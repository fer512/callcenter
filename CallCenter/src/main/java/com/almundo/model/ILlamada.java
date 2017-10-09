package com.almundo.model;

import com.almundo.util.LlamadaEstados;

public interface ILlamada {

	public LlamadaEstados getEstado();
	public void setEstado(LlamadaEstados estado);
	public Integer getId();

}
