package Entities;

import java.time.LocalDate;
import java.time.Period;

import Enums.FormaPgto;
import Enums.StatusPgto;

public class Pagamento {
	
	/*
	 * id

sessao (relacionamento com a sessão/atendimento)

valor

dataPagamento

formaPagamento (Dinheiro, Cartão, Pix, Transferência, etc.)

status (Pago, Pendente, Cancelado)
	 */
	
	private int id;
	private Sessao sessao;
	private double valor;
	private LocalDate data_pagamento;
	private FormaPgto forma_pgto;
	private StatusPgto status_pgto;
	
	public Pagamento() {
		
	}

	public Pagamento(int id, Sessao sessao, double valor, LocalDate data_pagamento, FormaPgto forma_pgto,
			StatusPgto status_pgto) {
		this.id = id;
		this.sessao = sessao;
		this.valor = valor;
		this.data_pagamento = data_pagamento;
		this.forma_pgto = forma_pgto;
		this.status_pgto = status_pgto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(LocalDate data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public FormaPgto getForma_pgto() {
		return forma_pgto;
	}

	public void setForma_pgto(FormaPgto forma_pgto) {
		this.forma_pgto = forma_pgto;
	}

	public StatusPgto getStatus_pgto() {
		return status_pgto;
	}

	public void setStatus_pgto(StatusPgto status_pgto) {
		this.status_pgto = status_pgto;
	}
	
	
	
	
	
}
