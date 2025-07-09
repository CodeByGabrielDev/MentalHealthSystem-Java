package Entities;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {

	/*
	 * id
	 * 
	 * nome
	 * 
	 * cpf
	 * 
	 * rg
	 * 
	 * dataNascimento
	 * 
	 * telefone
	 * 
	 * email
	 * 
	 * endereco
	 */
	private int id;
	private String nome;
	private String cpf;
	private String rg;
	private LocalDate data_nascimento;
	private String telefone;
	private String email;
	private String endereco;

	public Pessoa() {

	}

	public Pessoa(int id, String nome, String cpf, String rg, LocalDate data_nascimento, String telefone, String email,
			String endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.data_nascimento = data_nascimento;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public static String formatadorCpf(String cpf) {
		if (cpf.contains("-") || cpf.contains(".")) {
			return cpf.replace("-", "").replace(".", "").trim();
		} else {
			return cpf;
		}

	}

	public static String formatadorRg(String rg) {
		if (rg.contains("-") || rg.contains(".")) {
			return rg.replace("-", "").replace(".", "").trim();

		} else {
			return rg;
		}

	}

	public static int calcularIdade(LocalDate data) {
		LocalDate data_atual = LocalDate.now();
		Period calculo = Period.between(data, data_atual);
		int idade = calculo.getYears();
		return idade;

	}

}
