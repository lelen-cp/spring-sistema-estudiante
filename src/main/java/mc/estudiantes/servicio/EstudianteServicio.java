 package mc.estudiantes.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.estudiantes.modelo.Estudiante;
import mc.estudiantes.repositorio.EstudianteRepositorio;

@Service
public class EstudianteServicio implements IEstudianteServicio {
	
	@Autowired
	private EstudianteRepositorio estudianteRepositorio;

	@Override
	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> estudiantes = estudianteRepositorio.findAll();
		return estudiantes;
	}

	@Override
	public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
		//Metodo para encontrar mediante id un registro y retornar null en caso de no existir el ID
		Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
		return estudiante;
	}

	@Override
	public void guardarEstudiante(Estudiante estudiante) {
		//El metodo save sirve tanto para insercion como actualizacion
		estudianteRepositorio.save(estudiante);
		
	}

	@Override
	public void eliminarEstudiante(Estudiante estudiante) {
		estudianteRepositorio.delete(estudiante); 
		
	}

	

}
