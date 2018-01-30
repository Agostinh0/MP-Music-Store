package br.ufrpe.mp_music_store.negocio;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.dados.RepositorioCds;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
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

	public void adicionaCds(Cd c) throws ObjectExistException{
		if(c == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(this.repositorio.existe(c.getTitulo())) {
			throw new ObjectExistException();
		}
		else {
			repositorio.cadastrar(c);
		}
	}

	public Cd buscarCds(String nome) throws ObjectNotExistException{
		if(nome == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(!this.repositorio.existe(nome)) {
			throw new ObjectNotExistException();
		}

		return this.repositorio.procurar(nome);
	}

	public void atualizarCds(String pesquisa, Cd c) throws ObjectNotExistException, ErroAtualizarException{
		if(c == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.atualizar(pesquisa, c);
		}
	}

	public void removerCds(String nome) throws ObjectNotExistException, ErroRemoverException{
		if(nome == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else {
			repositorio.remover(nome);
		}
	}

	public boolean existeCd(String titulo) {

		return this.repositorio.existe(titulo);
	}

	public ArrayList<Cd> listarCds() {
		return this.repositorio.listar();
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}
