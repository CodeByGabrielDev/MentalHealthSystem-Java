package Enums;

public enum statusPaciente {
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;
	
	statusPaciente(String descricao){
		this.descricao = descricao;
	}
	
	
	public static statusPaciente fromDescricao(String descricao) {
		for(statusPaciente stats:statusPaciente.values()) {
			if(stats.descricao.equalsIgnoreCase(descricao)) {
				return stats;
			}
			
		}
		throw new IllegalArgumentException("erro");
	}
	
}
