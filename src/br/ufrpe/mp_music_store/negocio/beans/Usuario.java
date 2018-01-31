package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -9132525567846031220L;
	private String username;
	private String password;
	
	//Construtor
	public Usuario(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	//Métodos Getters e Setters
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	//toString
	public String toString(){
		String texto = "Login: " + this.getUsername();
		texto += "\nSenha: " + this.getPassword();
		return texto;
	}
	
	//Método equals
	public boolean equals(Usuario user){
		boolean r;
		if(user != null && this.username != null && this.password != null){
			r = (this.username.equals(user.getUsername()));
		}else{
			r = false;
		}
		
		return r;
		
	}
}
