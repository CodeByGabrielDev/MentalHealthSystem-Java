package Interface;

import java.util.List;

import Entities.*;
public interface PagamentoDAO {

	Pagamento callPagamentos(int id);

	List<Pagamento> selectAll();

	void insert(Pagamento pgto);

	void update(Pagamento pgto);

	void delete(int id);
}
