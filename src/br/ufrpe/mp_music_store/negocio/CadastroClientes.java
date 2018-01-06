package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.dados.RepositorioClientes;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
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
	
	public void adicionarCliente(Cliente c) throws ObjectExistException{
		if(c == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(this.repositorio.existe(c.getCpf())) {
			throw new ObjectExistException();
		}
		else {
			repositorio.cadastrar(c);
		}
	}
	
	public Cliente buscarCliente(long pesquisa) throws ObjectNotExistException{
		if(pesquisa == 0) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(!this.repositorio.existe(pesquisa)) {
			throw new ObjectNotExistException();
		}
		
		return this.repositorio.buscar(pesquisa);
	}
	
	public void atualizarCliente(long pesquisa, Cliente cliente) throws ObjectNotExistException, ErroAtualizarException{
		if(cliente == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.atualizar(pesquisa, cliente);
		}
	}
	
	public void removerCliente(long cpf) throws ObjectNotExistException, ErroRemoverException{
		if(cpf == 0) {
			throw new IllegalArgumentException("Entrada Inválida.");
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
