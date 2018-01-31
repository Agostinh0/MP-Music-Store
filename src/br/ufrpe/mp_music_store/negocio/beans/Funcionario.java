package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;

import br.ufrpe.mp_music_store.enumeracoes.TipoFuncionario;

public class Funcionario extends Pessoa implements Serializable{

	private static final long serialVersionUID = -3312454688878667498L;
	private float salario;
	private int numContrato;
	private TipoFuncionario tipoFunc;
	private Usuario usuario;

	//Construtor
	public Funcionario(String nome, long cpf, String endereco,
			long telefone, float salario, int numContrato, Usuario usuario, TipoFuncionario tipoFunc){
		super(nome, cpf, endereco, telefone);
		this.salario = salario;
		this.numContrato = numContrato;
		this.tipoFunc = tipoFunc;
		this.usuario = usuario;
	}

	public boolean isAdm() {
		boolean teste = false;
		
		if(this.tipoFunc == TipoFuncionario.ADM) {
			teste = true;
		}
		
		return teste;
	}
	
	//Metodos Getters e Setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(int numContrato) {
		this.numContrato = numContrato;
	}
	
	public TipoFuncionario getTipoFuncionario(){
		return tipoFunc;
	}
	
	public void setTipoFuncionario(TipoFuncionario tipoFunc){
		this.tipoFunc = tipoFunc;
	}
	
	public String toString(){
		String texto = "\nNome: " + this.getNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nTipo de Funcionário: " + this.getTipoFuncionario();
		texto += "\nEndereço: " + this.getEndereco();
		texto += "\nTelefone: " + this.getTelefone();
		texto += "\nSalário: " + this.getSalario();
		texto += "\nNúmero de contrato: " + this.getNumContrato();
		return texto;
	}
}
