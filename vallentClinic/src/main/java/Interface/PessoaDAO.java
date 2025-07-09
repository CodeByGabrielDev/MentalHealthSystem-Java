package Interface;
import java.util.List;

import Entities.*;
public interface PessoaDAO {

	Pessoa callPessoa(int id);

	List<Pessoa> selectAll();

	void insert(Pessoa pessoa);

	void update(Pessoa pessoa);

	void delete(int id);
}
