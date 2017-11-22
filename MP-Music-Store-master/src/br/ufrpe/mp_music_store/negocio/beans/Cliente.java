package br.ufrpe.mp_music_store.negocio.beans;

public class Cliente extends Pessoa{
	
	private int numCadastro;
	
	//Construtor
	public Cliente(String nome, int cpf, String endereco, int telefone, int numCadastro) {
		super(nome, cpf, endereco, telefone);
		this.numCadastro = numCadastro;
	}
	
	
	//Metodos Getters e Setters
	public void setNumCadastro(int numCadastro){
		this.numCadastro = numCadastro;
	}
	
	public int getNumCadastro(){
		return this.numCadastro;
	}
	
}
