package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public class RepositorioFuncionarios implements IRepositorioFuncionarios{
	
	private Funcionario[] funcionario;
	private int proxima;
	private static RepositorioFuncionarios instance;
	
	//Construtor
	public RepositorioFuncionarios(int tamanho){
		this.funcionario = new Funcionario[tamanho];
		this.proxima = 0;
	}
	
	//Singleton
		public static RepositorioFuncionarios getInstance() {
			if(instance == null) {
				instance = new RepositorioFuncionarios(100);
			}
			
			return instance;
		}
	
	//Cadastrar Funcionário
	public void cadastrar(Funcionario f){
		this.funcionario[this.proxima] = f;
		this.proxima += 1;
		if(this.proxima == funcionario.length){
			this.duplicarArrayFuncionarios();
		}
	}
	
	public void cadastrar(String nome, long cpf, String endereco, long tel, float salario, 
			int numContrato){
		Funcionario f = new Funcionario(nome, cpf, endereco, tel, salario, numContrato);
		this.cadastrar(f);
	}
	
	//Buscar funcionário por cpf
	public Funcionario buscar(long cpf) throws ObjectNotExistException{
		int i = this.procurarIndice(cpf);
		Funcionario resultado = null;
		if(i != this.proxima){
			resultado = this.funcionario[i];
		}
		else {
			throw new ObjectNotExistException();
		}
		
		return resultado;
	}
	
	//Buscar índice
	public int procurarIndice(long cpf){
		int i = 0;
		boolean achou = false;
		
		while((!achou) && (i < this.proxima)){
			if(cpf == this.funcionario[i].getCpf()){
				achou = true;
			}else{
				i += 1;
			}
		}
		return i;
	}
	
	//Verificar se funcionário existe
	public boolean existe(long cpf){
		boolean existe = false;
		int indice = this.procurarIndice(cpf);
		if(indice != proxima){
			existe = true;
		}
		
		return existe;
	}
	
	//Atualizar informações do CD
		public void atualizar(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException{
			int i = procurarIndice(pesquisa);
			
			if(i >= 0){
				this.funcionario[i].setNome(f.getNome());
				this.funcionario[i].setCpf(f.getCpf());
				this.funcionario[i].setEndereco(f.getEndereco());
				this.funcionario[i].setTelefone(f.getTelefone());
				this.funcionario[i].setSalario(f.getSalario());
				this.funcionario[i].setNumContrato(f.getNumContrato());
			}
			else {
				throw new ErroAtualizarException();
			}
			
		}
	
	//Remover do array de funcionários
	public void remover(long cpf) throws ObjectNotExistException, ErroRemoverException{
		int i = this.procurarIndice(cpf);
		if(i != this.proxima){
			this.funcionario[i] = this.funcionario[this.proxima - 1];
			this.funcionario[this.proxima] = null;
			this.proxima = this.proxima - 1;
		}else{
			throw new ErroRemoverException();
		}
	}
	
	//Duplicar tamanho do array de funcionários
	public void duplicarArrayFuncionarios(){
		if(this.funcionario != null && this.funcionario.length > 0){
			Funcionario arrayDuplicado[] = new Funcionario[this.funcionario.length * 2];
			
			for(int i = 0; i < this.funcionario.length; i++){
				arrayDuplicado[i] = this.funcionario[i];
			}
			
			this.funcionario = arrayDuplicado;
		}
	}	
}