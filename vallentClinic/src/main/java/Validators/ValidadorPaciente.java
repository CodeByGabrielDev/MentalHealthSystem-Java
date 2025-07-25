package Validators;

import Entities.Convenio;
import Entities.Paciente;
import Enums.statusPaciente;

public class ValidadorPaciente {

	public static void validador(Paciente paciente) {

		paciente.setNumero_prontuario(validadorProntuario(paciente.getNumero_prontuario()));
		paciente.setConvenio(validadorConvenio(paciente.getConvenio()));
		paciente.setObs(validadorObs(paciente.getObs()));
		

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

	
}
