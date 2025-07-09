package Interface;
import java.util.List;

import Entities.*;
public interface PsicologoDAO {
	
	Psicologo callConvenio(int id);

	List<Psicologo> selectAll();

	void insert(Psicologo convenio);

	void update(Psicologo convenio);

	void delete(int id);
}
