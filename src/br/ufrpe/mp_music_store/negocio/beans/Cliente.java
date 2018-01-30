package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;

import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;

public class Cliente extends Pessoa implements Serializable{

	private static final long serialVersionUID = 5896291882567492865L;
	private Cd[] cdsObtidos;
	private int numCadastro;
	private int indice;
	private TipoCliente tipoDeCliente;

	//Construtor
	public Cliente(String nome, long cpf, String endereco, long telefone, int numCadastro, TipoCliente tipoDeCliente) {
		super(nome, cpf, endereco, telefone);
		this.indice = 0;
		this.numCadastro = numCadastro;
		this.cdsObtidos = new Cd[100];
		this.tipoDeCliente = tipoDeCliente;
	}

	//Metodos Getters e Setters
	public void setNumCadastro(int numCadastro){
		this.numCadastro = numCadastro;
	}

	public int getNumCadastro(){
		return this.numCadastro;
	}

	public void setTipoCliente(TipoCliente tipoDeCliente) {
		this.tipoDeCliente = tipoDeCliente;
	}

	public TipoCliente getTipoCliente() {
		return tipoDeCliente;
	}

	public void compraCds(Cd obtido) {
		this.cdsObtidos[this.indice] = obtido;
		this.indice++;
	}

	public int getIndice() {
		return this.indice;
	}

	public String toString(){
		String texto = "\nNome: " + this.getNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nTipo de cliente: " + this.getTipoCliente();
		texto += "\nEndereço: " + this.getEndereco();
		texto += "\nTelefone: " + this.getTelefone();
		texto += "\nNúmero de cadastro: " + this.getNumCadastro() + "\n";
		return texto;
	}

}