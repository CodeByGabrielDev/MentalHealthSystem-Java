package Entities;

import java.time.LocalDate;

import Enums.statusPaciente;

public class Paciente extends Pessoa{
	
	/*
	 * numeroProntuario

convenio (relacionamento com Convenio)

observacoes

	 */
	
	private int numero_prontuario;
	private Convenio convenio;
	private String obs;
	private statusPaciente stats;
	
	public Paciente() {
		
	}

	public Paciente(int id, String nome, String cpf, String rg, LocalDate data_nascimento, String telefone,
			String email, String endereco, int numero_prontuario, Convenio convenio, String obs,statusPaciente stats) {
		super(id, nome, cpf, rg, data_nascimento, telefone, email, endereco);
		this.numero_prontuario = numero_prontuario;
		this.convenio = convenio;
		this.obs = obs;
		this.stats = stats;
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

	public statusPaciente getStats() {
		return stats;
	}

	public void setStats(statusPaciente stats) {
		this.stats = stats;
	}
	
	
	
	
	
	

}
