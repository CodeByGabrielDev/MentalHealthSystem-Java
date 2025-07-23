package Validators;

import Entities.Psicologo;
import Enums.EspecialidadesPsi;

public class ValidadorPsicologo {

	public static void validador(Psicologo psi) {
		psi.setCrp(validadorCrp(psi.getCrp()));
		psi.setEspecialidade(validadorEspecialidade(psi.getEspecialidade()));

	}

	private static String validadorCrp(String crp) {
		if (crp == null || crp.isBlank()) {
			throw new IllegalArgumentException("Argumento nao pode estar vazio ou null");
		} else {
			for (char c : crp.toCharArray()) {
				if (!Character.isDigit(c)) {
					throw new IllegalArgumentException("formato de CRP invalido, apenas insira a numeração do CRP ");

				}

			}
			if (crp.length() != 8) {
				throw new IllegalArgumentException(
						"formato de CRP invalido, esta fora de formatação, o crp contem 8 digitos ");

			}
			return crp;
		}

	}

	private static EspecialidadesPsi validadorEspecialidade(EspecialidadesPsi especialidade) {
		// criar um validador, possivelmente validador estará diretamente no ENUM
		return especialidade;
	}

}
