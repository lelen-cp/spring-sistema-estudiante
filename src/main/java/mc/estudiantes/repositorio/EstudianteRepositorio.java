package mc.estudiantes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mc.estudiantes.modelo.Estudiante;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {

}
