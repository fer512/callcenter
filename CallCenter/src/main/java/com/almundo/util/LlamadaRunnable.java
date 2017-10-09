package com.almundo.util;

import com.almundo.model.IEmpleado;
import com.almundo.model.ILlamada;

public class LlamadaRunnable implements Runnable {

	RandomDuracion randomDuracion = new RandomDuracion();
	IEmpleado empleado = null;
	ILlamada llamada = null;
	ICallback callback;
	
	public LlamadaRunnable(IEmpleado emp, ILlamada llamada,ICallback callback) {
		this.empleado = emp;
		this.llamada = llamada;
		this.callback = callback;
	}

	public void run() {
		llamada.setEstado(LlamadaEstados.ENCURSO);
		int segundos = randomDuracion.esperar();
		try {
			Thread.sleep(segundos * 1000);
		} catch (Exception ex) {
			Thread.currentThread().interrupt();
		}finally {
			llamada.setEstado(LlamadaEstados.FINALIZADA);
			empleado.setDisponible(true);
			callback.callback();
		}
	}

}
