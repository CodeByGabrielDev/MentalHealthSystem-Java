package Validators;

import java.time.LocalDate;

import Entities.Pagamento;
import Entities.Sessao;
import Enums.FormaPgto;
import Enums.StatusPgto;

public class ValidadorPgto {

	public static void validador(Pagamento pgto) {
		pgto.setSessao(validadorSessao(pgto.getSessao()));
		pgto.setValor(validadorValor(pgto.getValor()));
		pgto.setData_pagamento(validadorData(pgto.getData_pagamento()));
		pgto.setStatus_pgto(validadorPgto(pgto.getStatus_pgto()));

	}

	private static Sessao validadorSessao(Sessao session) {
		if (session == null) {
			throw new IllegalArgumentException("a sessão nao pode ser null");
		} else {
			return session;
		}

	}

	private static double validadorValor(Double value) {
		if (value == null || value <= 0) {
			throw new IllegalArgumentException("valor nao pode ser menor que zero ou null");
		} else {
			return value;
		}

	}

	private static LocalDate validadorData(LocalDate data_pagamento) {
		if (data_pagamento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("A data nao pode ser posteriora a data ATUAL");

		} else {
			return data_pagamento;
		}

	}

	private static StatusPgto validadorPgto(StatusPgto statusPgto) {
		// criar um validador, no momento sem ideias.
		return statusPgto;
	}

}
