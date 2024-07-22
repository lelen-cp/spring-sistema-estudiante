package mc.estudiantes;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mc.estudiantes.modelo.Estudiante;
import mc.estudiantes.servicio.EstudianteServicio;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner{
	
	@Autowired
	private EstudianteServicio estudianteServicio;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(EstudiantesApplication.class);
	
	String nl = System.lineSeparator();
	
	
	public static void main(String[] args) {
		logger.info("Iniciando aplicacion...");
		
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Ejecutando metodo run de Spring...");
		
		var salir = false;
		var consola = new Scanner(System.in);
		
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}
	
	private void mostrarMenu() {
		logger.info(nl);
		logger.info("""
				*** Sistema de Estudiantes ***
				1. Listar Estudiantes
				2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Elige una opción:""");
	}
	
	private boolean ejecutarOpciones(Scanner consola) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		
		switch (opcion) {
		case 1 -> { //Listar Estudiantes
			logger.info(nl + "Listado de Estudiantes: " + nl);
			List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
			estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
		}
		
		case 2 -> {//Buscar estudiante por id
			logger.info("Introduce el Id del estudiante a buscar: ");	
			var idEstudiante = Integer.parseInt(consola.nextLine());
			Estudiante estudiante = 
					estudianteServicio.buscarEstudiantePorId(idEstudiante);
			if(estudiante != null) {
				logger.info("Estudiante encontrado: " + estudiante + nl);
			}else 
				logger.info("Estudiante no encontrado por id: " + idEstudiante + nl);
		}
		case 3 -> {// Agregar estudiante
			logger.info("Agregar Estudiante: " + nl);
			logger.info("Nombre: ");
			var nombre = consola.nextLine();
			logger.info("Apellido: ");
			var apellido = consola.nextLine();
			logger.info("Telefono: ");
			var telefono = consola.nextLine();
			logger.info("Email: ");
			var email = consola.nextLine();
			
			//Crear el objeto estudiante sin el id
			var estudiante = new Estudiante();
			estudiante.setNombre(nombre);
			estudiante.setApellido(apellido);
			estudiante.setTelefono(telefono);
			estudiante.setEmail(email);
			estudianteServicio.guardarEstudiante(estudiante);
			logger.info("Estudiante agregado: " + estudiante + nl);
		}
		case 4 -> {// Modificar estudiante por id
			logger.info("Modificar Estudiante: " + nl);
			logger.info("Id Estudiante: ");
			var idEstudiante = Integer.parseInt(consola.nextLine());
			// Buscamos el estudiante a modficiar
			Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
			
			if(estudiante != null) {
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante modificado: " + estudiante + nl);
			}
			else 
				logger.info("Estudiante no encontrado con id: " + idEstudiante + nl);
		}	
			case 5 ->{//Eliminar estudiante
				logger.info("Eliminar Estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscar el id a eliminar
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante !=null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante + nl);
					
				}
				else
					logger.info("Estudiante no encontrado con id: " + idEstudiante);
			}
			
			case 6 ->{//Salir
				logger.info("Hasta pronto..." + nl + nl);
				salir = true;
			}
			
			default -> logger.info("Opcion no reconocida: " + opcion + nl);
	}//Fin switch
		return salir;
	}

}
