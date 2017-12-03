package br.ufrpe.mp_music_store.negocio.beans;

public class Cliente extends Pessoa{
	
	private int numCadastro;
	
	//Construtor
	public Cliente(String nome, long cpf, String endereco, long telefone, int numCadastro) {
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
	
	public String toString(){
		String texto = "\nNome: " + this.getNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nEndereço " + this.getEndereco();
		texto += "\nTelefone: " + this.getTelefone();
		texto += "\nNúmero de cadastro: " + this.getNumCadastro() + "\n";
		return texto;
	}
	
}
