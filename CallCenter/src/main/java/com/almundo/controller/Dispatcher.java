package com.almundo.controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.almundo.model.IEmpleado;
import com.almundo.model.ILlamada;
import com.almundo.service.EmpleadoService;
import com.almundo.util.ICallback;
import com.almundo.util.LlamadaRunnable;

public class Dispatcher implements ICallback {

	static final Integer MAX_CANTIDAD_LLAMADAS = 10;
	private Queue<ILlamada> llamadasPendientes;
	ExecutorService executor;
	EmpleadoService empleadoService;
	
	public void dispatchCall(ILlamada llamada){
		IEmpleado emp = empleadoService.obtenerEmpleado();
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
		Integer cantidad = threadPoolExecutor.getActiveCount();
		if(emp != null && cantidad < MAX_CANTIDAD_LLAMADAS) {
            Runnable llamadaAtendida = new LlamadaRunnable(emp, llamada,this);
            executor.execute(llamadaAtendida);
		}else {
			llamadasPendientes.add(llamada);
		}
	}
	
	public void atenderPendiente(){
		ILlamada llamada =  llamadasPendientes.poll();
		if(llamada != null) this.dispatchCall(llamada);
	}
	
	public Dispatcher(EmpleadoService empleadoService) {
		super();
		this.executor = Executors.newFixedThreadPool(MAX_CANTIDAD_LLAMADAS);
		this.llamadasPendientes = new LinkedList<ILlamada>();
		this.empleadoService = empleadoService;
	}

	public Queue<ILlamada> getLlamadasPendientes() {
		return llamadasPendientes;
	}

	public void setLlamadasPendientes(Queue<ILlamada> llamadasPendientes) {
		this.llamadasPendientes = llamadasPendientes;
	}

	public void callback() {
		this.atenderPendiente();
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}
	
	
}
