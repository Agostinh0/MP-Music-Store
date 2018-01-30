package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public class RepositorioClientes implements IRepositorioClientes{

	private Cliente[] cliente;
	private int proxima;
	private static RepositorioClientes instance;
	private ArrayList<Cliente> listaClientes = new ArrayList<>();

	//Construtor
	public RepositorioClientes(int tamanho){
		this.cliente = new Cliente[tamanho];
		this.proxima = 0;
	}

	//Singleton
	public static RepositorioClientes getInstance() {
		if(instance == null) {
			instance = new RepositorioClientes(100);
		}

		return instance;
	}

	//Cadastrar Cliente
	public void cadastrar(Cliente c){
		this.listaClientes.add(c);
		this.cliente[this.proxima] = c;
		this.proxima += 1;
		if(this.proxima == cliente.length){
			this.duplicarArrayClientes();
		}
	}

	public void cadastrar(String nome, long cpf, String endereco, long tel, int numCadastro, boolean tipoCliente){
		Cliente cl = new Cliente(nome, cpf, endereco, tel, numCadastro, tipoCliente);
		this.cadastrar(cl);
	}

	//Buscar cliente por CPF
	public Cliente buscar(long cpf) throws ObjectNotExistException{
		int i = this.procurarIndice(cpf);
		Cliente resultado = null;
		if(i != this.proxima){
			resultado = this.cliente[i];
		}
		else {
			throw new ObjectNotExistException();
		}
		return resultado;
	}

	//Buscar índice
	private int procurarIndice(long cpf){
		int i = 0;
		boolean achou = false;

		while((!achou) && (i < this.proxima)){
			if(cpf == this.cliente[i].getCpf()){
				achou = true;
			}else{
				i += 1;
			}
		}
		return i;
	}

	//Verficar se cliente existe
	public boolean existe(long cpf){
		boolean existe = false;
		int indice = this.procurarIndice(cpf);
		if(indice != proxima){
			existe = true;
		}
		return existe;
	}

	//Atualizar informações do Cliente
	public void atualizar(long pesquisa, Cliente cliente)
			throws ObjectNotExistException, ErroAtualizarException{
		int i = procurarIndice(pesquisa);

		if(i >= 0){
			this.listaClientes.set(i, cliente);
			this.cliente[i].setNome(cliente.getNome());
			this.cliente[i].setCpf(cliente.getCpf());
			this.cliente[i].setEndereco(cliente.getEndereco());
			this.cliente[i].setTelefone(cliente.getTelefone());
			this.cliente[i].setNumCadastro(cliente.getNumCadastro());
			this.cliente[i].setClientePremium(cliente.getClienteTypePremium());
		}
		else {
			throw new ErroAtualizarException();
		}

	}

	//Remover do array de clientes
	public void remover(long cpf) throws ObjectNotExistException, ErroRemoverException{
		int i = this.procurarIndice(cpf);
		if(i != this.proxima){
			this.listaClientes.remove(i);
			this.cliente[i] = this.cliente[this.proxima - 1];
			this.cliente[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}else{
			throw new ErroRemoverException();
		}
	}

	//Dobrar tamanho do array de clientes
	public void duplicarArrayClientes(){
		if(this.cliente != null && this.cliente.length > 0){
			Cliente arrayDuplicado[] = new Cliente[this.cliente.length * 2];

			for(int i = 0; i < this.cliente.length; i++){
				arrayDuplicado[i] = this.cliente[i];
			}	
			this.cliente = arrayDuplicado;
		}	
	}

	public ArrayList<Cliente> listar() {
		return this.listaClientes;
	}
}