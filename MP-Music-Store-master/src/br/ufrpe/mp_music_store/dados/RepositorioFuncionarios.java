package br.ufrpe.mp_music_store.dados;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;

public class RepositorioFuncionarios {
	
	private Funcionario[] funcionario;
	private int proxima;
	
	//Construtor
	public RepositorioFuncionarios(int tamanho){
		this.funcionario = new Funcionario[tamanho];
		this.proxima = 0;
	}
	
	//Cadastrar Funcion�rio
	public void cadastrar(Funcionario f){
		this.funcionario[this.proxima] = f;
		this.proxima += 1;
		if(this.proxima == funcionario.length){
			this.duplicarArrayFuncionarios();
		}
	}
	
	public void cadastrar(String nome, int cpf, String endereco, int tel, float salario, 
			int numContrato){
		Funcionario f = new Funcionario(nome, cpf, endereco, tel, salario, numContrato);
		this.cadastrar(f);
	}
	
	//Buscar funcion�rio por cpf
	public Funcionario buscar(int cpf){
		int i = this.procurarIndice(cpf);
		Funcionario resultado = null;
		if(i != this.proxima){
			resultado = this.funcionario[i];
		}
		
		return resultado;
	}
	
	//Buscar �ndice
	public int procurarIndice(int cpf){
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
	
	//Verificar se funcion�rio existe
	public boolean existe(int cpf){
		boolean existe = false;
		int indice = this.procurarIndice(cpf);
		if(indice != proxima){
			existe = true;
		}
		
		return existe;
	}
	
	//Remover do array de funcion�rios
	public void remover(int cpf){
		int i = this.procurarIndice(cpf);
		if(i != this.proxima){
			this.funcionario[i] = this.funcionario[this.proxima - 1];
			this.funcionario[this.proxima] = null;
			this.proxima = this.proxima - 1;
		}else{
			
		}
	}
	
	//Duplicar tamanho do array de funcion�rios
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