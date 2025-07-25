package Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Enums.FormaPgto;
import Enums.TipoMovimentacao;

public class Movimentacao {

/*
 * ID int(11) AI PK 
DATA_MOVIMENTACAO datetime 
VALOR decimal(10,2) 
ID_CLIENTE int(11) 
DESCRICAO varchar(200) 
TIPO varchar(50) 
FORMA_PGTO varchar(40) 
FATURADA tinyint(4) 
ID_PSICOLOGO int(11) 
ID_CONVENIO int(11) 
ESTORNADA tinyint(4) 
CANCELADA tinyint(4) 
DATA_CANCELAMENTO datetime
 */

	private int id;
	private LocalDateTime data_movimentacao;
	private double valor;
	private Paciente paciente;
	private String descricao;
	private TipoMovimentacao tipo;
	private FormaPgto forma_pgto;
	private int faturada;
	private Psicologo psicologo;
	private Convenio convenio;
	private int estornada;
	private int cancelada;
	private LocalDateTime data_cancelamento = null;

	
	public Movimentacao() {

	}

	public Movimentacao(int id, double valor, Paciente paciente, String descricao, TipoMovimentacao tipo,
			FormaPgto forma_pgto, int faturada, Psicologo psicologo, Convenio convenio) {
		this.id = id;
		this.data_movimentacao = LocalDateTime.now();
		this.valor = valor;
		this.paciente = paciente;
		this.descricao = descricao;
		this.tipo = tipo;
		this.forma_pgto = forma_pgto;
		this.faturada = faturada;
		this.psicologo = psicologo;
		this.convenio = convenio;
		estornada = 0;
		cancelada = 0;
		data_cancelamento = null;
	}

	public Movimentacao(LocalDateTime data_movimentacao,double valor, Paciente paciente, String descricao, TipoMovimentacao tipo, FormaPgto forma_pgto,
			int faturada, Psicologo psicologo, Convenio convenio) {
		this.data_movimentacao = data_movimentacao;
		this.valor = valor;
		this.paciente = paciente;
		this.descricao = descricao;
		this.tipo = tipo;
		this.forma_pgto = forma_pgto;
		this.faturada = faturada;
		this.psicologo = psicologo;
		this.convenio = convenio;
		estornada = 0;
		cancelada = 0;
		data_cancelamento = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public FormaPgto getForma_pgto() {
		return forma_pgto;
	}

	public void setForma_pgto(FormaPgto forma_pgto) {
		this.forma_pgto = forma_pgto;
	}

	public int getFaturada() {
		return faturada;
	}

	public void setFaturada(int faturada) {
		this.faturada = faturada;
	}

	public Psicologo getPsicologo() {
		return psicologo;
	}

	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public int getEstornada() {
		return estornada;
	}

	public void setEstornada(int estornada) {
		this.estornada = estornada;
	}

	public LocalDateTime getData_movimentacao() {
		return data_movimentacao;
	}

	public void setData_movimentacao(LocalDateTime data_movimentacao) {
		this.data_movimentacao = data_movimentacao;
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
