package Validators;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Entities.Paciente;
import Entities.Psicologo;
import Entities.Sessao;
import Enums.StatusSessao;

public class ValidadorSessao {

	public static void validador(Sessao session) {
		/*
		 * 	private int id;
	private Paciente paciente;
	private Psicologo psicologo;
	private LocalDate data_hora;
	private double valor;
	private StatusSessao status;
	private String obs;
		 */
		session.setPaciente(validadorPaciente(session.getPaciente()));
		session.setPsicologo(validadorPsicologo(session.getPsicologo()));
		session.setData_hora(validadorData(session.getData_hora()));
		session.setValor(validadorValor(session.getValor()));
		session.setStatus(validadorSessao(session.getStatus()));
		session.setObs(validadorObs(session.getObs()));
		
	}
	private static Paciente validadorPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new IllegalArgumentException("Nao pode ser instanciada uma sessao sem um paciente vinculado a ela");

		} else {
			return paciente;
		}

	}

	private static Psicologo validadorPsicologo(Psicologo psicologo) {
		if (psicologo == null) {
			throw new IllegalArgumentException("Nao pode ser instanciada uma sessao sem um psicologo vinculado a ela");

		} else {
			return psicologo;
		}

	}

	private static LocalDateTime validadorData(LocalDateTime data_hora) {
		if (data_hora.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("A sessão nao pode ser marcada em uma data posteriora a atual.");

		} else {
			return data_hora;
		}

	}

	private static double validadorValor(Double value) {
		if (value == null || value <= 0) {
			throw new IllegalArgumentException("O valor nao pode ser inferior ou igual a zero");

		} else {
			return value;
		}

	}

	private static StatusSessao validadorSessao(StatusSessao status) {
		// AINDA SEM IDEIA DE IMPLEMENTAÇÃO
		return status;

	}

	private static String validadorObs(String obs) {
		if (obs.isBlank()) {
			return null;

		} else {
			return obs;
		}
	}

}
