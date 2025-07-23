package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Enums.StatusSessao;

public class Sessao {
	/*
	 * ✅ Sessao ou Atendimento id
	 * 
	 * paciente (relacionamento)
	 * 
	 * psicologo (relacionamento)
	 * 
	 * dataHora
	 * 
	 * duracao (em minutos)
	 * 
	 * valor
	 * 
	 * status (Agendado, Concluído, Cancelado, etc.)
	 */

	private int id;
	private Paciente paciente;
	private Psicologo psicologo;
	private LocalDateTime data_hora;
	private double valor;
	private StatusSessao status;
	private String obs;

	public Sessao() {

	}

	public Sessao(int id, Paciente paciente, Psicologo psicologo, LocalDateTime data_hora, double valor,
			StatusSessao status, String obs) {
		this.id = id;
		this.paciente = paciente;
		this.psicologo = psicologo;
		this.data_hora = data_hora;
		this.valor = valor;
		this.status = status;
		this.obs = obs;
	}

	public Sessao(Paciente paciente, Psicologo psicologo, LocalDateTime data_hora, double valor, StatusSessao status,
			String obs) {
		this.paciente = paciente;
		this.psicologo = psicologo;
		this.data_hora = data_hora;
		this.valor = valor;
		this.status = status;
		this.obs = obs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Psicologo getPsicologo() {
		return psicologo;
	}

	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public void setData_hora(LocalDateTime data_hora) {
		this.data_hora = data_hora;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public StatusSessao getStatus() {
		return status;
	}

	public void setStatus(StatusSessao status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
