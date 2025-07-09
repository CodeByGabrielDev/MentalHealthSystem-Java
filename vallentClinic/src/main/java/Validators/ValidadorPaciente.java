package Validators;

import Entities.Convenio;
import Entities.Paciente;
import Enums.statusPaciente;

public class ValidadorPaciente {

	public static void validador(Paciente paciente) {
		validadorProntuario(paciente.getNumero_prontuario());
		validadorConvenio(paciente.getConvenio());
		validadorObs(paciente.getObs());
		validadorStatus(paciente.getStats());

	}

	private static int validadorProntuario(Integer numero) {
		if (numero == null || numero <= 0) {
			throw new IllegalArgumentException("o prontuario nao pode ser null ou zero");

		} else {
			return numero;

		}

	}

	private static Convenio validadorConvenio(Convenio convenio) {
		if (convenio == null) {
			throw new IllegalArgumentException("nao pode ser instanciado o OBJETO PACIENTE sem o CONVENIO");
		} else {
			return convenio;
		}
	}

	private static String validadorObs(String obs) {
		if (obs == null) {
			return "null";
		}
		return obs;

	}

	private static statusPaciente validadorStatus(statusPaciente status) {
		// ainda implementar, sem ideias.
		return status;
	}
}
