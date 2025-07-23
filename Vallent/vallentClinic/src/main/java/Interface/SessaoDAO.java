package Interface;
import java.util.List;

import Entities.*;
public interface SessaoDAO {

	Sessao callSessao(int id);

	List<Sessao> selectAll();

	void insert(Sessao sessao);

	void update(Sessao sessao);

	void delete(int id);
}
