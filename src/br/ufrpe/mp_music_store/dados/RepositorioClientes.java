package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public class RepositorioClientes implements IRepositorioClientes, Serializable{

	private static final long serialVersionUID = -2527751057426987886L;
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
			instance = RepositorioClientes.lerArquivo();
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

	public void cadastrar(String nome, long cpf, String endereco, long tel, int numCadastro, TipoCliente tipoCliente){
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
			this.cliente[i].setTipoCliente(cliente.getTipoCliente());
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

	//Arquivos
		private static RepositorioClientes lerArquivo(){
			RepositorioClientes instanciaLocal = null;
			
			File arquivo = new File("repositorioClientes.dat");
			
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try{
				
				fis = new FileInputStream(arquivo);
				ois = new ObjectInputStream(fis);
				
				Object o = ois.readObject();
			
				instanciaLocal = (RepositorioClientes) o;
				
			}catch(Exception e){
				instanciaLocal = new RepositorioClientes(100);
			
			}finally{
				if(ois != null){
					try{
						ois.close();
					}catch(IOException e){
						
					}
				}
			}
			
			return instanciaLocal;
			
		}
		
		public void salvarArquivo(){
			if(instance == null){
				return;
			}
			
			File arquivo = new File("repositorioClientes.dat");
			
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			
			try{
				if(!arquivo.exists()){
					arquivo.createNewFile();
				}
				
				fos = new FileOutputStream(arquivo);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(instance);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(oos != null){
					try{
						oos.close();
					}catch(IOException e){
						
					}
				}
			}
			
		}
}