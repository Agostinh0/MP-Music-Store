package br.ufrpe.mp_music_store.enumeracoes;

public enum TipoCliente {
	PREMIUM("Premium"), NORMAL("Normal");
	
	public String tipoCliente;
	
	TipoCliente(String tipoCliente){
		this.tipoCliente = tipoCliente;
	}
	
	public String getTipoCliente(){
		return tipoCliente;
	}
}
