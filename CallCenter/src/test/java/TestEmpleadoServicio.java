import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.almundo.controller.Dispatcher;
import com.almundo.model.Director;
import com.almundo.model.IEmpleado;
import com.almundo.model.Operador;
import com.almundo.model.Supervisor;
import com.almundo.service.EmpleadoService;

public class TestEmpleadoServicio {

	EmpleadoService empleadoService;
	Dispatcher dispatcher;
	
	@Before
	public void before() {
		
		List<IEmpleado> directores = new ArrayList<IEmpleado>();
		directores.add(new Director("director1"));
		
		List<IEmpleado> supervisores = new ArrayList<IEmpleado>();
		supervisores.add(new Supervisor("supervisor1"));
		
		List<IEmpleado> operadores = new ArrayList<IEmpleado>();
		operadores.add(new Operador("operador1"));
		
		Map<Integer, List<IEmpleado>> empleadosMap = new HashMap<Integer, List<IEmpleado>>();
		empleadosMap.put(0,operadores);
		empleadosMap.put(1,supervisores);
		empleadosMap.put(2,directores);

		empleadoService = new EmpleadoService(empleadosMap);
		dispatcher = new Dispatcher(empleadoService);
	}
	
	@Test
	public void obtenerOperador() {
		IEmpleado emp = empleadoService.obtenerEmpleado();
		assertTrue(emp.getNombre().equalsIgnoreCase("operador1"));
	}
	
	@Test
	public void obtenerSupervisor() {
		//operador
		empleadoService.obtenerEmpleado();
		
		IEmpleado sup = empleadoService.obtenerEmpleado();
		assertTrue(sup.getNombre().equalsIgnoreCase("supervisor1"));
	}
	
	@Test
	public void obtenerDirector() {
		//operador
		empleadoService.obtenerEmpleado();
		
		//supervisor
		empleadoService.obtenerEmpleado();
				
		IEmpleado dir = empleadoService.obtenerEmpleado();
		assertTrue(dir.getNombre().equalsIgnoreCase("director1"));
	}
}
