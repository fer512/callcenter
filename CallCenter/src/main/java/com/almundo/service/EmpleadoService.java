package com.almundo.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.almundo.model.IEmpleado;

public class EmpleadoService {

	private Map<Integer, List<IEmpleado>> empleadosMap = new HashMap<Integer, List<IEmpleado>>();

	public synchronized IEmpleado obtenerEmpleado() {
		Iterator<Integer> it = empleadosMap.keySet().iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			List<IEmpleado> empleados = empleadosMap.get(key);
			for (IEmpleado emp : empleados) {
				if (emp.isDisponible()) {
					emp.setDisponible(false);
					return emp;
				}
			}

		}

		return null;
	}

	public EmpleadoService(Map<Integer, List<IEmpleado>> empleadosMap2) {
		this.empleadosMap = empleadosMap2;
	}

	public Map<Integer, List<IEmpleado>> getEmpleadosMap() {
		return empleadosMap;
	}

	public void setEmpleadosMap(Map<Integer, List<IEmpleado>> empleados) {
		this.empleadosMap = empleados;
	}

}
