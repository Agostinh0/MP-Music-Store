package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;

public class Cd implements Serializable{

	private static final long serialVersionUID = 3350453139452868726L;
	private String titulo;
	private int anoLancamento;
	private String artista;
	private double preco;

	//Construtor
	public Cd(String titulo, int anoLancamento, String artista, float preco) {
		super();
		this.titulo = titulo;
		this.anoLancamento = anoLancamento;
		this.artista = artista;
		this.preco = preco;
	}

	//Métodos Getters e Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	//Método toString
	public String toString(){
		String texto = "\nTítulo: " + this.getTitulo();
		texto += "\nAno de lançamento: " + this.getAnoLancamento();
		texto += "\nArtista: " + this.getArtista();
		texto += "\nPreço : R$" + this.getPreco();
		return texto;
	}

	//Método Equals
	public boolean equals(Cd c){
		boolean r;
		if(c != null && this.titulo != null && this.anoLancamento != 0){
			r = (this.titulo.equals(c.getTitulo()) 
					&& this.anoLancamento == c.getAnoLancamento());
		}else{
			r = false;
		}

		return r;
	}

}