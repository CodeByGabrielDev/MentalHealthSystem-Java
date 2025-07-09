package Validators;

import Entities.Convenio;

public class ValidadorConvenio {

	public static void validador(Convenio convenio) {
		validarNome(convenio.getNome());
		validarCnpj(convenio.getCnpj());
		validarTelefone(convenio.getTelefone());

	}
	/*
	 * private int id; private String nome; private String cnpj; private String
	 * telefone; private String email;
	 */

	private static String validarNome(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException("nome nao pode estar vazio ou null");

		} else {
			return nome;
		}

	}

	private static String validarCnpj(String cnpj) {
		if (cnpj == null || cnpj.isBlank()) {
			throw new IllegalArgumentException("o cnpj não pode estar vazio");
		} else {
			for (char c : cnpj.toCharArray()) {
				if (Character.isLetter(c)) {
					throw new IllegalArgumentException("o cnpj nao pode conter numeros.");

				}

			}
			return cnpj.replace("/", "").replace("-", "").replace(".", "").trim();
		}

	}

	private static String validarTelefone(String telefone) {
		if (telefone == null || telefone.isBlank()) {
			throw new IllegalArgumentException("o telefone nao pode estar null ou vazio");
		} else {
			for (char c : telefone.toCharArray()) {
				if (Character.isLetter(c)) {
					throw new IllegalArgumentException("o telefone nao pode ser uma letra");
				}

			}
			return telefone.replace("-", "").replace(".", "").replace("+", "").trim();
		}

	}

}
