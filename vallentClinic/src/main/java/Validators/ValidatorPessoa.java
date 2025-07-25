package Validators;

import java.time.LocalDate;

import Entities.Pessoa;

public class ValidatorPessoa {

	public static void validar(Pessoa pessoa) {
		pessoa.setNome(validadorNome(pessoa.getNome()));
		pessoa.setCpf(validadorCpf(pessoa.getCpf()));
		pessoa.setRg(validadorRg(pessoa.getRg()));
		pessoa.setData_nascimento(validadorDate(pessoa.getData_nascimento()));
		pessoa.setTelefone(formatadorTelefone(pessoa.getTelefone()));
		pessoa.setEmail(validadorEmail(pessoa.getEmail()));
		pessoa.setEndereco(validadorEndereco(pessoa.getEndereco()));

	}

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
		String format = cpf.replace(" ", "").replace("/", "").replace("-", "").replace(".", "").trim();
		if (cpf.isBlank() || cpf == null) {
			throw new IllegalArgumentException("o cpf nao pode ser null ou vazio.");

		}
		if (format.length() > 11 || format.length() < 11) {
			throw new IllegalArgumentException("Erro, o cpf deve conter 11 caracteres");
		} else {
			return format;
		}
	}

	private static String validadorRg(String rg) {
		if (rg.isBlank() || rg == null) {
			throw new IllegalArgumentException("o cpf nao pode ser null ou vazio.");

		} else {
			for (char c : rg.toCharArray()) {
				if (Character.isLetter(c)) {
					throw new IllegalArgumentException("RG NAO PODE CONTER LETRAS");

				}

			}
			String format = rg.replace(" ", "").replace("/", "").replace("-", "").replace(".", "").trim();
			if (format.length() > 9 || format.length() < 9) {
				throw new IllegalArgumentException("não pode conter mais numeros que 9 ou menor");
			}
			return format;
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
			return telefone.replace(".", "").replace("-", "").replace("(", "").replace(")", "");

		}

	}

	private static String validadorEmail(String email) {
		if (email.isBlank() || email == null) {
			throw new IllegalArgumentException("o email nao pode possuir espaço em branco");
		} else {
			if (!email.contains("@")) {
				throw new IllegalArgumentException("formato de email incorreto.");

			}
			return email;
		}

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
