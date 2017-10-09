import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Before;
import org.junit.Test;

import com.almundo.controller.Dispatcher;
import com.almundo.model.Director;
import com.almundo.model.IEmpleado;
import com.almundo.model.ILlamada;
import com.almundo.model.Llamada;
import com.almundo.model.Operador;
import com.almundo.model.Supervisor;
import com.almundo.service.EmpleadoService;

public class TestDispacher {

	EmpleadoService empleadoService;
	Dispatcher dispatcher;
	
	@Before
	public void before() {
		List<IEmpleado> directores = new ArrayList<IEmpleado>();
		directores.add(new Director("director1"));
		
		List<IEmpleado> supervisores = new ArrayList<IEmpleado>();
		supervisores.add(new Supervisor("supervisor1"));
		supervisores.add(new Supervisor("supervisor2"));
		supervisores.add(new Supervisor("supervisor3"));
		
		List<IEmpleado> operadores = new ArrayList<IEmpleado>();
		operadores.add(new Operador("operador1"));
		operadores.add(new Operador("operador2"));
		operadores.add(new Operador("operador3"));
		operadores.add(new Operador("operador4"));
		operadores.add(new Operador("operador5"));
		operadores.add(new Operador("operador6"));
		
		Map<Integer, List<IEmpleado>> empleadosMap = new HashMap<Integer, List<IEmpleado>>();
		empleadosMap.put(0,operadores);
		empleadosMap.put(1,supervisores);
		empleadosMap.put(2,directores);
		
		empleadoService = new EmpleadoService(empleadosMap);
		dispatcher = new Dispatcher(empleadoService);
	}
	
	@Test
	public void atender1llamada() {
		ILlamada llamada1 = new Llamada();
		dispatcher.dispatchCall(llamada1);
		
		ExecutorService e = dispatcher.getExecutor();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) e;
		Integer cantidad = executor.getActiveCount();
		
		assertTrue(cantidad.equals(1));
	}
	
	@Test
	public void atender10llamada() {
		ILlamada llamada1 = new Llamada();
		ILlamada llamada2 = new Llamada();
		ILlamada llamada3 = new Llamada();
		ILlamada llamada4 = new Llamada();
		ILlamada llamada5 = new Llamada();
		ILlamada llamada6 = new Llamada();
		ILlamada llamada7 = new Llamada();
		ILlamada llamada8 = new Llamada();
		ILlamada llamada9 = new Llamada();
		ILlamada llamada10 = new Llamada();
		
		ExecutorService e = dispatcher.getExecutor();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) e;
		dispatcher.dispatchCall(llamada1);	
		dispatcher.dispatchCall(llamada2);		
		dispatcher.dispatchCall(llamada3);		
		dispatcher.dispatchCall(llamada4);		
		dispatcher.dispatchCall(llamada5);		
		dispatcher.dispatchCall(llamada6);		
		dispatcher.dispatchCall(llamada7);
		dispatcher.dispatchCall(llamada8);
		dispatcher.dispatchCall(llamada9);
		dispatcher.dispatchCall(llamada10);
		
		Integer cantidad = executor.getActiveCount();
		
		assertTrue(cantidad.equals(10));
	}
	
	@Test
	public void atenderSolo10llamada() {
		ILlamada llamada1 = new Llamada();
		ILlamada llamada2 = new Llamada();
		ILlamada llamada3 = new Llamada();
		ILlamada llamada4 = new Llamada();
		ILlamada llamada5 = new Llamada();
		ILlamada llamada6 = new Llamada();
		ILlamada llamada7 = new Llamada();
		ILlamada llamada8 = new Llamada();
		ILlamada llamada9 = new Llamada();
		ILlamada llamada10 = new Llamada();
		ILlamada llamada11 = new Llamada();
		
		ExecutorService e = dispatcher.getExecutor();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) e;
		
		dispatcher.dispatchCall(llamada1);	
		dispatcher.dispatchCall(llamada2);		
		dispatcher.dispatchCall(llamada3);		
		dispatcher.dispatchCall(llamada4);		
		dispatcher.dispatchCall(llamada5);		
		dispatcher.dispatchCall(llamada6);		
		dispatcher.dispatchCall(llamada7);
		dispatcher.dispatchCall(llamada8);
		dispatcher.dispatchCall(llamada9);
		dispatcher.dispatchCall(llamada10);
		dispatcher.dispatchCall(llamada11);
		
		Integer cantidad = executor.getActiveCount();
		
		assertTrue(cantidad.equals(10));
	}
}
