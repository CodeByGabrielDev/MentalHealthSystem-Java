package Interface;
import java.util.List;

import Entities.*;
public interface SessaoDAO {

	Sessao callConvenio(int id);

	List<Sessao> selectAll();

	void insert(Sessao convenio);

	void update(Sessao convenio);

	void delete(int id);
}
