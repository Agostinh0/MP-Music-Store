package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = -7214000520983546932L;
	private String nome;
	private long cpf;
	private String endereco;
	private long telefone;

	//Construtor
	public Pessoa(String nome, long cpf, String endereco, long telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	//Métodos Getters e Setters
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpfCliente) {
		this.cpf = cpfCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefoneCliente) {
		this.telefone = telefoneCliente;
	}


}
