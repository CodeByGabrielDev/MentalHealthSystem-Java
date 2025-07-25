package Enums;

public enum StatusSessao {

	AGENDADO("AGENDADO"), 
	CONCLUIDO("CONCLUIDO"), 
	CANCELADO("CANCELADO");
	
	
	private String descricao;
	
	StatusSessao(String descricao){
		this.descricao = descricao;
	}
	
	
	public static StatusSessao fromDescricao(String descricao) {
		for(StatusSessao stats:StatusSessao.values()) {
			if(stats.descricao.equalsIgnoreCase(descricao)) {
				return stats;
			}
			
		}
		throw new IllegalArgumentException("erro");
	}

}
