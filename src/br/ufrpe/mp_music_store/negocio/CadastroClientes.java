package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioClientes;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;


public class CadastroClientes {
	private RepositorioClientes repositorio;
	private static CadastroClientes instance;
	
	private CadastroClientes() {
		this.repositorio = RepositorioClientes.getInstance();
	}
	
	public static CadastroClientes getInstance() {
		if(instance == null) {
			instance = new CadastroClientes();
		}
		
		return instance;
	}
	
	public void adicionarCliente(Cliente c) {
		if(c == null) {
			//error message
		}
		else {
			repositorio.cadastrar(c);
		}
	}
	
	public Cliente buscarCliente(long pesquisa) {
		if(pesquisa == 0) {
			//return error message
		}
		
		return this.repositorio.buscar(pesquisa);
	}
	
	public void atualizarCliente(String nome, long cpfCliente, String endereco, long telefoneCliente, int numCadastro){
		if(cpfCliente == 0){
			//error message
		}else{
			repositorio.atualizar(nome, cpfCliente, endereco, telefoneCliente, numCadastro);
		}
	}
	
	public void removerCliente(long cpf) {
		if(cpf == 0) {
			//error message
		}
		else {
			repositorio.remover(cpf);
		}
	}
	
	public boolean existeCliente(long cpf) {
		if(cpf == 0) {
			//return error message
		}
		
		return this.repositorio.existe(cpf);
	}
}
