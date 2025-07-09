package Validators;

import java.time.LocalDate;

public class ValidatorPessoa {

	public static void validar() {

	}
	/*
	 * private int id; private String nome; private String cpf; private String rg;
	 * private LocalDate data_nascimento; private String telefone; private String
	 * email; private String endereco;
	 */

	// METODOS AUXILIARES QUE IRAO REALIZAR A VALIDAÇÃO NO METODO ACIMA.
	private static String validadorNome(String nome) {
		if (nome.isBlank() || nome == null) {
			throw new IllegalArgumentException("O nome nao pode estar vazio ou null");

		} else {
			for (char c : nome.toCharArray()) {
				if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
					throw new IllegalArgumentException(
							"O nome nao pode ter caracteres especiais ou espaços em branco.");
				}

			}
			return nome;
		}
	}

	private static String validadorCpf(String cpf) {
		if (cpf.isBlank() || cpf == null) {
			throw new IllegalArgumentException("o cpf nao pode ser null ou vazio.");

		} else {
			if (cpf.contains("/") || cpf.contains("-") || cpf.contains(".") || cpf.contains(" ")) {
				return cpf.replace(" ", "").replace("/", "").replace("-", "").replace(".", "").trim();
			} else {
				return cpf;
			}
		}
	}

	private static String validadorRg(String rg) {
		if (rg.isBlank() || rg == null) {
			throw new IllegalArgumentException("o cpf nao pode ser null ou vazio.");

		} else {
			if (rg.contains("/") || rg.contains("-") || rg.contains(".") || rg.contains(" ")) {
				return rg.replace(" ", "").replace("/", "").replace("-", "").replace(".", "").trim();
			} else {
				return rg;
			}
		}
	}

	private static LocalDate validadorDate(LocalDate data_nascimento) {
		if (data_nascimento == null || data_nascimento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("nao pode ultrapassar o dia atual ou ser null.");
		} else {
			return data_nascimento;
		}
	}

	private static String formatadorTelefone(String telefone) {
		if (telefone.isBlank() || telefone == null) {
			throw new IllegalArgumentException("o telefone nao pode estar vazio.");
		} else {
			for (char c : telefone.toCharArray()) {
				if (Character.isAlphabetic(c)) {
					throw new IllegalArgumentException("os caracteres nao podem ser letras");

				}
			}
			return telefone.replace(".", "").replace("-", "");

		}

	}

	private static String validadorEmail(String email) {
		if (email.isBlank() || email == null) {
			throw new IllegalArgumentException("o email nao pode possuir espaço em branco");
		}
		return email;

	}

	private static String validadorEndereco(String endereco) {
		if (endereco.isBlank() || endereco == null) {
			throw new IllegalArgumentException("o endereco nao pode conter espaços em branco");

		} else {
			for (char c : endereco.toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					throw new IllegalArgumentException("endereco não pode conter caracteres especiais");
				}

			}
			return endereco;
		}

	}

	// metodos validadores

}
