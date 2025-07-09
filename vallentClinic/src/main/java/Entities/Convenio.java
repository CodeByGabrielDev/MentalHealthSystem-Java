package Entities;

public class Convenio {

	private int id;
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;

	public Convenio() {

	}

	public Convenio(int id, String nome, String cnpj, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static String formatadorCnpj(String cnpj) {
		if (cnpj.contains(".") || cnpj.contains("/") || cnpj.contains("-")) {
			return cnpj.replace(".", "").replace("/", "").replace("-", "").trim();
		} else {
			return cnpj;
		}

	}
	
	public static String formatadorTelefone(String telefone) {
		if(telefone.contains("(") || telefone.contains(")")||telefone.contains("-")) {
			return telefone.replace("(", "").replace(")", "").replace("-", "").trim();
			
		}else {
			return telefone;
		}
	}
}
