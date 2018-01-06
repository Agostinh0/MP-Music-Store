package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioFuncionarios;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;

public class CadastroFuncionarios {
	private RepositorioFuncionarios repositorio;
	private static CadastroFuncionarios instance;
	
	private CadastroFuncionarios() {
		this.repositorio = RepositorioFuncionarios.getInstance();
	}
	
	public static CadastroFuncionarios getInstance() {
		if(instance == null) {
			instance = new CadastroFuncionarios();
		}
		
		return instance;
	}
	
	public void adicionaFuncionario(Funcionario f) throws ObjectExistException{
		if(f == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(this.repositorio.existe(f.getCpf())) {
			throw new ObjectExistException();
		}
		else {
			repositorio.cadastrar(f);
		}
	}
	
	public Funcionario buscarFuncionario(long cpf) throws ObjectNotExistException{
		if(cpf == 0) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(!this.repositorio.existe(cpf)) {
			throw new ObjectNotExistException();
		}
		
		return this.repositorio.buscar(cpf);
	}
	
	public void atualizar(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException{
		if(f == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.atualizar(pesquisa, f);
		}
	}
	
	public void removerFuncionario(long cpf) throws ObjectNotExistException, ErroRemoverException{
		if(cpf == 0) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else {
			repositorio.remover(cpf);
		}
	}
	
	public boolean existeFuncionario(long cpf) {
		if(cpf == 0) {
			//return fudeu amigo
		}
		
		return this.repositorio.existe(cpf);
	}
}