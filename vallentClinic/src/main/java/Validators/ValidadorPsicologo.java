package Validators;

import Entities.Psicologo;
import Enums.EspecialidadesPsi;

public class ValidadorPsicologo {

	public static void validador(Psicologo psi) {
		psi.setCrp(validadorCrp(psi.getCrp()));
		psi.setEspecialidade(validadorEspecialidade(psi.getEspecialidade()));

	}

	private static String validadorCrp(String crp) {
		if (crp.isBlank() || crp == null) {
			throw new IllegalArgumentException("Argumento nao pode estar vazio ou null");
		} else {
			for (char c : crp.toCharArray()) {
				if (Character.isLetter(c)) {
					throw new IllegalArgumentException("o crp nao pode conter letras");

				}

			}
			return crp.replace("-", "").replace("/", "").replace(".", "").trim();
		}

	}

	private static EspecialidadesPsi validadorEspecialidade(EspecialidadesPsi especialidade) {
		// criar um validador, possivelmente validador estará diretamente no ENUM
		return especialidade;
	}

}
