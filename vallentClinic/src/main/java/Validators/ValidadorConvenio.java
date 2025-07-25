package Validators;

import Entities.Convenio;

public class ValidadorConvenio {

	public static void validador(Convenio convenio) {
		convenio.setNome(validarNome(convenio.getNome()));
		convenio.setCnpj(validarCnpj(convenio.getCnpj()));
		convenio.setTelefone(validarTelefone(convenio.getTelefone()));
		convenio.setEmail(validadorEmail(convenio.getEmail()));

	}
	/*
	 * private int id; private String nome; private String cnpj; private String
	 * telefone; private String email;
	 */

	private static String validarNome(String nome) {
		if (nome.isEmpty() || nome == null) {
			throw new IllegalArgumentException("nome nao pode estar vazio ou null");

		} else {
			for (char c : nome.toCharArray()) {
				if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new IllegalArgumentException("o nome nao pode conter NUMEROS, revise os dados");
				}

			}
			return nome;
		}

	}

	private static String validarCnpj(String cnpj) {
		if (cnpj == null || cnpj.isBlank()) {
			throw new IllegalArgumentException("o cnpj não pode estar vazio");
		}
		for (char c : cnpj.toCharArray()) {
			if (Character.isLetter(c)) {
				throw new IllegalArgumentException("o cnpj nao pode conter LETRAS, revise os dados.");

			}

		}
		String formatador = cnpj.replace("/", "").replace("-", "").replace(".", "");
		return formatador;
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

	private static String validadorEmail(String email) {
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("O email nao pode estar em branco, revise os dados");
		} else {
			for (char c : email.toCharArray()) {
				if (Character.isSpaceChar(c)) {
					throw new IllegalArgumentException("O email nao pode conter espaços em branco");

				}

			}
			return email;
		}

	}

}
