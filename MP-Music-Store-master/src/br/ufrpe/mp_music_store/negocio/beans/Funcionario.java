package br.ufrpe.mp_music_store.negocio.beans;

public class Funcionario extends Pessoa{
	
	private float salario;
	private int numContrato;
	
	//Construtor
	public Funcionario(String nome, int cpf, String endereco,
			int telefone, float salario, int numContrato) {
		super(nome, cpf, endereco, telefone);
		this.salario = salario;
		this.numContrato = numContrato;
	}
	
	//Metodos Getters e Setters
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
	
	
}
