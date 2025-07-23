package Validators;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Entities.Movimentacao;
import Entities.NotaFiscal;
import Entities.Paciente;

public class ValidadorNotaFiscal {
	public static void validar(NotaFiscal nota) {
		nota.setNumero_nota(validadorNumeroNota(nota.getNumero_nota()));
		nota.setId_movi(validadorMovimentacao(nota.getId_movi()));
		nota.setEmissao(validadorEmissao(nota.getEmissao()));
		nota.setValor_nota(validadorValor(nota.getValor_nota()));
		nota.setDesc(validadorDesc(nota.getDesc()));
		nota.setPaciente(validadorPaciente(nota.getPaciente()));
	}

	private static int validadorNumeroNota(int numero_nota) {
		if (numero_nota < 0) {
			throw new IllegalArgumentException("erro, o numero da nota nao pode ser menor que zero.");

		} else {
			return numero_nota;
		}

	}

	private static Movimentacao validadorMovimentacao(Movimentacao movi) {
		if (movi == null) {
			throw new IllegalArgumentException(
					"a movimentação nao pode estar nula, para emissao de uma nota precisa estar vinculado diretamente a uma movimentação.");
		} else {
			return movi;
		}

	}

	private static LocalDateTime validadorEmissao(LocalDateTime emissao) {
		if (emissao.isAfter(LocalDateTime.now())) {
			throw new RuntimeException("a emissao nao pode estar superior a data atual, contate o suporte.");
		} else {
			return emissao;
		}
	}

	private static double validadorValor(double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("o valor da nota nao pode estar inferior a 0");
		} else {
			return valor;
		}

	}

	private static String validadorDesc(String desc) {
		if (desc == null || desc.isBlank()) {
			return null;

		} else {
			return desc;
		}
	}

	private static Paciente validadorPaciente(Paciente paci) {
		if (paci == null) {
			throw new IllegalArgumentException("É necessario e vinculação de um cliente na nota, revise os dados");
		} else {
			return paci;
		}
	}

}
