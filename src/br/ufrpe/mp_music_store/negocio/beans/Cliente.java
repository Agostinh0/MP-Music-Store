package br.ufrpe.mp_music_store.negocio.beans;

public class Cliente extends Pessoa{
	
	private Cd[] cdsObtidos;
	private int numCadastro;
	private boolean premium;
	private int indice;
	
	//Construtor
	public Cliente(String nome, long cpf, String endereco, long telefone, int numCadastro, boolean premium) {
		super(nome, cpf, endereco, telefone);
		this.premium = premium;
		this.indice = 0;
		this.numCadastro = numCadastro;
		this.cdsObtidos = new Cd[100];
	}
	
	
	//Metodos Getters e Setters
	public void setNumCadastro(int numCadastro){
		this.numCadastro = numCadastro;
	}
	
	public int getNumCadastro(){
		return this.numCadastro;
	}
	
	public void setClientePremium(boolean tipoCliente) {
		this.premium = tipoCliente;
	}
	
	public boolean getClienteTypePremium() {
		if(this.premium) {
			return true;
		}
		return false;
	}
	
	public void compraCds(Cd obtido) {
		this.cdsObtidos[this.indice] = obtido;
		this.indice++;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
	public String toString(){
		String texto;
		if(this.getClienteTypePremium()) {
			texto = "\nCliente Premium";
		}
		else {
			texto = "\nCliente Normal. Vire Premium!";
		}
		texto += "\nNome: " + this.getNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nEndereço " + this.getEndereco();
		texto += "\nTelefone: " + this.getTelefone();
		texto += "\nNúmero de cadastro: " + this.getNumCadastro() + "\n";
		return texto;
	}
	
}