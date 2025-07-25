package Interface;

import java.util.List;

import Entities.NotaFiscal;

public interface NotaFiscalDAO {
	
	NotaFiscal selectId(int id);
	
	NotaFiscal callNota(int numero_nota);

	List<NotaFiscal> selectAll();

	
}
