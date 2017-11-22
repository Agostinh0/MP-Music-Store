package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioCds;
import br.ufrpe.mp_music_store.negocio.beans.Cd;

public class CatalogoCds {
	private RepositorioCds repositorio;
	private static CatalogoCds instance;
	
	private CatalogoCds() {
		this.repositorio = RepositorioCds.getInstance();
	}
	
	public static CatalogoCds getInstance() {
		if(instance == null) {
			instance = new CatalogoCds();
		}
		
		return instance;
	}
	
	public void adicionaCds(Cd c) {
		if(c == null) {
			//error message
		}
		else {
			repositorio.cadastrar(c);
		}
	}
	
	public Cd buscarCds(String nome) {
		if(nome == null) {
			//return error message
		}
		return this.repositorio.procurar(nome);
	}
	
	public void removerCds(String nome) {
		if(nome == null) {
			//message error
		}
		else {
			repositorio.remover(nome);
		}
	}
	
	public boolean existeCd(String titulo) {
		if(titulo == null) {
			//return fudeu amigo
		}
		
		return this.repositorio.existe(titulo);
	}
}
