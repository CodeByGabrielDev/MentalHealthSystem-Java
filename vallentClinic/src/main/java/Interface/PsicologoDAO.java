package Interface;
import java.util.List;

import Entities.*;
public interface PsicologoDAO {
	
	Psicologo callPsicologo(int id);

	List<Psicologo> selectAll();

	void insert(Psicologo psi);

	void update(Psicologo psi);

	void delete(int id);
}
