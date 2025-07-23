package testes;

public class test {

	public static String validadorCpf(String cpf) {
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
}
