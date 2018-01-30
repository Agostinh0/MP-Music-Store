package br.ufrpe.mp_music_store.enumeracoes;

public enum TipoFuncionario {
	ADM("Administrador"), COMUM("Comum");
	
	public String tipoFuncionario;
	
	TipoFuncionario(String tipoFuncionario){
		this.tipoFuncionario = tipoFuncionario;
	}
	
	public String getTipoFuncionario(){
		return tipoFuncionario;
	}
}
