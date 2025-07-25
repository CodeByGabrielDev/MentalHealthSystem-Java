package Validators;

import java.time.LocalDate;

import Entities.Convenio;
import Entities.Movimentacao;
import Entities.Paciente;
import Entities.Psicologo;

public class ValidadorMovimentacao {

	public static void validador(Movimentacao movimentacao) {
		movimentacao.setValor(validadorValor(movimentacao.getValor()));
		movimentacao.setPaciente(validadorPaciente(movimentacao.getPaciente()));
		movimentacao.setDescricao(validadorDesc(movimentacao.getDescricao()));
		movimentacao.setPsicologo(validadorPsi(movimentacao.getPsicologo()));
		movimentacao.setConvenio(validadorConvenio(movimentacao.getConvenio()));

	}

	private static double validadorValor(double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("o valor nao pode estar abaixo de zero");
		} else {
			return valor;
		}
	}

	private static Paciente validadorPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new IllegalArgumentException("o cliente nao pode estar vazio");
		} else {
			return paciente;
		}
	}

	private static String validadorDesc(String desc) {
		if (desc == null || desc.isBlank()) {
			return null;

		} else {
			return desc;
		}
	}

	private static Psicologo validadorPsi(Psicologo psi) {
		if (psi == null) {
			return null;
		} else {
			return psi;
		}
	}

	private static Convenio validadorConvenio(Convenio convenio) {
		if (convenio == null) {
			return null;
		} else {
			return convenio;
		}
	}

}
