package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioClientes;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;


public class CadastroClientes {
	private RepositorioClientes repositorio;
	private static CadastroClientes instance;
	
	private CadastroClientes() {
		this.repositorio = new RepositorioClientes(10);
	}
	
	
	public void adicionarCliente(Cliente c) {
		if(c == null) {
			//error message
		}
		else {
			repositorio.cadastrar(c);
		}
	}
	
	public Cliente buscarCliente(int cpf) {
		if(cpf == 0) {
			//return error message
		}
		
		return this.repositorio.buscar(cpf);
	}
	
	//Falta a atualização de geral. Alterar dados, etc.
	
	public void removerCliente(int cpf) {
		if(cpf == 0) {
			//error message
		}
		else {
			repositorio.remover(cpf);
		}
	}
	
	public boolean existeCliente(int cpf) {
		if(cpf == 0) {
			//return error message
		}
		
		return this.repositorio.existe(cpf);
	}
}
