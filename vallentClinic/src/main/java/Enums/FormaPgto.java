package Enums;

public enum FormaPgto {
	
	DINHEIRO("DINHEIRO"),
	CARTAO("CARTAO"),
	PIX("PIX"),
	TRANSFERENCIA("TRANSFERENCIA"),
	OUTRO("OUTRO");
	
	private String descricao;
	
	 FormaPgto(String descricao) {
		this.descricao = descricao;
	}
	public static FormaPgto fromDescricao(String descricao) {
		for(FormaPgto pgto:FormaPgto.values()) {
			if(pgto.descricao.equalsIgnoreCase(descricao)) {
				return pgto;
				
			}
			
		}
		throw new IllegalArgumentException("erro" );
	}
	
}
