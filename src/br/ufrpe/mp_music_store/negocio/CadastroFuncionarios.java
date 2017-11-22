package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioFuncionarios;
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
	
	public void adicionaFuncionario(Funcionario f) {
		if(f == null) {
			//error message
		}
		else {
			repositorio.cadastrar(f);
		}
	}
	
	public Funcionario buscarFuncionario(int cpf) {
		if(cpf == 0) {
			//return error message
		}
		return this.repositorio.buscar(cpf);
	}
	
	public void removerFuncionario(int cpf) {
		if(cpf == 0) {
			//message error
		}
		else {
			repositorio.remover(cpf);
		}
	}
	
	public boolean existeFuncionario(int cpf) {
		if(cpf == 0) {
			//return fudeu amigo
		}
		
		return this.repositorio.existe(cpf);
	}

}
