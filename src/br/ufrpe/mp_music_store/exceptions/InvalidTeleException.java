package br.ufrpe.mp_music_store.exceptions;

public class InvalidTeleException extends Exception{

	private static final long serialVersionUID = -2137102970853917162L;
	
	public InvalidTeleException() {
		super("Número de Telefone Inválido!");
	}
}
