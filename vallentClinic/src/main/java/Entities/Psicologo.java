package Entities;

import java.time.LocalDate;

import Enums.EspecialidadesPsi;

public class Psicologo extends Pessoa {

	private String crp;
	private EspecialidadesPsi especialidade;
	private String horario;

	public Psicologo(int id, String nome, String cpf, String rg, LocalDate data_nascimento, String telefone,
			String email, String endereco) {
		super(id, nome, cpf, rg, data_nascimento, telefone, email, endereco);
	}

	public Psicologo(int id, String nome, String cpf, String rg, LocalDate data_nascimento, String telefone,
			String email, String endereco, String crp, EspecialidadesPsi especialidade, String horario) {
		super(id, nome, cpf, rg, data_nascimento, telefone, email, endereco);
		this.crp = crp;
		this.especialidade = especialidade;
		this.horario = horario;
	}

	public String getCrp() {
		return crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}

	public EspecialidadesPsi getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadesPsi especialidade) {
		this.especialidade = especialidade;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	

}
