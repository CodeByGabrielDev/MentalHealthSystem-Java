package Interface;

import java.util.List;

import Entities.*;

public interface PacienteDAO {
	
	Paciente callPacientes(int id);

	List<Paciente> selectAll();

	void insert(Paciente paciente);

	void update(Paciente paciente);

	void delete(int id);
}
