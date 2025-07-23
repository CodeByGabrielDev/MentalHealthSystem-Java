package Enums;

public enum TipoMovimentacao {

	CONSULTA_PARTICULAR("CONSULTA_PARTICULAR"), CONSULTA_CONVENIO("CONSULTA_CONVENIO"),
	AVALIACAO_PSICOLOGICA("AVALIACAO_PSICOLOGICA"), REEMBOLSO("REEMBOLSO"), VENDA_PRODUTO("VENDA_PRODUTO"),
	ESTORNO("ESTORNO");

	private String descricao;

	TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoMovimentacao fromDescricao(String descricao) {
		for (TipoMovimentacao stats : TipoMovimentacao.values()) {
			if (stats.descricao.equalsIgnoreCase(descricao)) {
				return stats;
			}

		}
		throw new IllegalArgumentException("erro");
	}

}
