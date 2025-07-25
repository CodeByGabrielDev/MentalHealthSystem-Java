package Interface;

import java.util.List;

import Entities.Movimentacao;
import Entities.NotaFiscal;

public interface MovimentacaoDAO {

	Movimentacao callMovimentacao(int id);

	List<Movimentacao> selectAll();

	void insert(Movimentacao mov);


	void cancelamentoDeMovimentacao(int id);
}
