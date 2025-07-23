package Entities;

import java.sql.Date;
import java.time.LocalDate;

import Enums.statusPaciente;

public class Paciente extends Pessoa {

	/*
	 * numeroProntuario
	 * 
	 * convenio (relacionamento com Convenio)
	 * 
	 * observacoes
	 * 
	 */

	private int numero_prontuario;
	private Convenio convenio;
	private String obs;
	private int ativo;

	public Paciente() {

	}

	public Paciente(int id, String nome, String cpf, String rg, LocalDate data_nascimento, String telefone,
			String email, String endereco,int numero_prontuario, Convenio convenio, String obs,int id2) {
		super(id, nome, cpf, rg, data_nascimento, telefone, email, endereco);
		this.numero_prontuario = numero_prontuario;
		this.convenio = convenio;
		this.obs = obs;
		id2 = id;
	}
	public Paciente(String nome, String cpf, String rg, LocalDate data_nascimento, String telefone,
			String email, String endereco, int numero_prontuario, Convenio convenio, String obs) {
		super(0, nome, cpf, rg, data_nascimento, telefone, email, endereco);
		this.numero_prontuario = numero_prontuario;
		this.convenio = convenio;
		this.obs = obs;

	}
	

	public int getNumero_prontuario() {
		return numero_prontuario;
	}

	public void setNumero_prontuario(int numero_prontuario) {
		this.numero_prontuario = numero_prontuario;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getStats() {
		return ativo;
	}

	public void setStats(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "numero de prontuario do paciente " +super.getNome()+ " é " +numero_prontuario+ ","
				+ "\n o convenio do mesmo é " +convenio+ " \n, observação: " +obs+ "\n, o status do paciente esta constando como: " +ativo;
	}
	
}
