package Interface;

import java.util.List;

import Entities.Convenio;
import Entities.*;


public interface ConvenioDAO {
	
	Convenio callConvenio(int id);

	List<Convenio> selectAll();

	void insert(Convenio convenio);

	void update(Convenio convenio);

	void delete(int id);
}
