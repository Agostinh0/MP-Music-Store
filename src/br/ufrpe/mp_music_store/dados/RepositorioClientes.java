package br.ufrpe.mp_music_store.dados;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;

public class RepositorioClientes {
	
	private Cliente[] cliente;
	private int proxima;
	private static RepositorioClientes instance;
	
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
		this.cliente[this.proxima] = c;
		this.proxima += 1;
		if(this.proxima == cliente.length){
			this.duplicarArrayClientes();
		}
	}
	
	public void cadastrar(String nome, int cpf, String endereco, int tel, int numCadastro){
		Cliente cl = new Cliente(nome, cpf, endereco, tel, numCadastro);
		this.cadastrar(cl);
	}
	
	//Buscar cliente por CPF
	public Cliente buscar(int cpf){
		int i = this.procurarIndice(cpf);
		Cliente resultado = null;
		if(i != this.proxima){
			resultado = this.cliente[i];
		}
		return resultado;
	}
	
	//Buscar �ndice
	private int procurarIndice(int cpf){
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
	public boolean existe(int cpf){
		boolean existe = false;
		int indice = this.procurarIndice(cpf);
		if(indice != proxima){
			existe = true;
		}
		return existe;
	}
	
	//Atualizar informa��es do Cliente
		public void atualizar(String nome, int cpf, String endereco, int tel, int numCadastro){
			int i = procurarIndice(cpf);
			
			if(i >= 0){
				this.cliente[i].setNome(nome);
				this.cliente[i].setCpf(cpf);
				this.cliente[i].setEndereco(endereco);
				this.cliente[i].setTelefone(tel);
				this.cliente[i].setNumCadastro(numCadastro);
			}
			
		}
	
	//Remover do array de clientes
	public void remover(int cpf){
		int i = this.procurarIndice(cpf);
		if(i != this.proxima){
			this.cliente[i] = this.cliente[this.proxima - 1];
			this.cliente[this.proxima] = null;
			this.proxima = this.proxima - 1;
		}else{
			
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
}
