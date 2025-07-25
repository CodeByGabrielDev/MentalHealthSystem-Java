package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotaFiscal {

	private int numero_nota;
	private String cfop;
	private Movimentacao id_movi;
	private LocalDateTime emissao = LocalDateTime.now();
	private double valor_nota;
	private String desc;
	private Paciente paciente;
	private String cnpjEmitente = "11.851.265/0001-10".replace(" ", "").replace("/", "").replace("-", "").replace(".",
			"");
	private int cancelada;
	private LocalDateTime data_cancelamento;

	public NotaFiscal() {

	}

	public NotaFiscal(int numero_nota, String cfop, Movimentacao id_movi, double valor_nota, String desc,
			Paciente paciente) {
		this.numero_nota = numero_nota;
		this.cfop = cfop;
		this.id_movi = id_movi;
		this.emissao = LocalDateTime.now();
		this.valor_nota = valor_nota;
		this.desc = desc;
		this.paciente = paciente;
		this.cnpjEmitente = "11.851.265/0001-10".replace(" ", "").replace("/", "").replace("-", "").replace(".", "");
		cancelada = 0;
		data_cancelamento = null;
	}

	public int getNumero_nota() {
		return numero_nota;
	}

	public void setNumero_nota(int numero_nota) {
		this.numero_nota = numero_nota;
	}

	public Movimentacao getId_movi() {
		return id_movi;
	}

	public void setId_movi(Movimentacao id_movi) {
		this.id_movi = id_movi;
	}

	public LocalDateTime getEmissao() {
		return emissao;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public void setEmissao(LocalDateTime emissao) {
		this.emissao = LocalDateTime.now();
	}

	public double getValor_nota() {
		return valor_nota;
	}

	public void setValor_nota(double valor_nota) {
		this.valor_nota = valor_nota;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getCnpjEmitente() {
		return cnpjEmitente;
	}

	public void setCnpjEmitente(String cnpj) {
		cnpjEmitente = cnpj;
	}

	public int getCancelada() {
		return cancelada;
	}

	public void setCancelada(int cancelada) {
		this.cancelada = cancelada;
	}

	public LocalDateTime getData_cancelamento() {
		return data_cancelamento;
	}

	public void setData_cancelamento(LocalDateTime data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}

}
