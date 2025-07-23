package Enums;

public enum EspecialidadesPsi {

	CLINICA("CLINICA"), 
	ESCOLAR("ESCOLAR"), 
	TRABALHO("TRABALHO"), 
	ESPORTE("ESPORTE"), 
	JURIDICA("JURIDICA"),
	HOSPITALAR("HOSPITALAR"), 
	PSICOPEDAGOGIA("PSICOPEDAGOGIA"), 
	PSICOMOTRICIDADE("PSICOMOTRICIDADE"), 
	SOCIAL("SOCIAL"),
	NEUROPSICOLOGIA("NEUROPSICOLOGIA"), 
	SAUDE("SAUDE");

	private String descricao;

	EspecialidadesPsi(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static EspecialidadesPsi fromDescricao(String esp) {
		for (EspecialidadesPsi espe : EspecialidadesPsi.values()) {
			if (espe.descricao.equalsIgnoreCase(esp.trim())) {
				return espe;

			}
			
		}
		throw new IllegalArgumentException("erro");

	}
}
