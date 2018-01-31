package br.ufrpe.mp_music_store.negocio;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.dados.RepositorioClientes;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.InvalidTeleException;
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

	public void adicionarCliente(Cliente c) throws ObjectExistException, InvalidCpfException, InvalidTeleException{
		if(c == null) {
			throw new IllegalArgumentException("Entrada Inválida.");
		}
		else if(String.valueOf(c.getCpf()).length() != 11) {
			throw new InvalidCpfException();
		}
		else if(String.valueOf(c.getTelefone()).length() < 11) {
			throw new InvalidTeleException();
		}
		else if(this.repositorio.existe(c.getCpf())) {
			throw new ObjectExistException();
		}
		else {
			repositorio.cadastrar(c);
		}
	}

	public Cliente buscarCliente(long pesquisa) throws ObjectNotExistException{
		if(!this.repositorio.existe(pesquisa)) {
			throw new ObjectNotExistException();
		}

		return this.repositorio.buscar(pesquisa);
	}

	public void atualizarCliente(long pesquisa, Cliente cliente) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException{
		if(cliente == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}
		else if(String.valueOf(cliente.getCpf()).length() != 11) {
			throw new InvalidCpfException();
		}
		else if(String.valueOf(cliente.getTelefone()).length() < 11) {
			throw new InvalidTeleException();
		}
		else{
			repositorio.atualizar(pesquisa, cliente);
		}
	}

	public void removerCliente(long cpf) throws ObjectNotExistException, ErroRemoverException{

		repositorio.remover(cpf);
	}

	public boolean existeCliente(long cpf) {

		return this.repositorio.existe(cpf);
	}

	public ArrayList<Cliente> listarCliente() {
		return this.repositorio.listar();
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}
