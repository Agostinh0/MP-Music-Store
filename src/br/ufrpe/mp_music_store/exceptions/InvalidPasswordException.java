package br.ufrpe.mp_music_store.exceptions;

public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 238898441701442012L;
	
	public InvalidPasswordException() {
		super("Senha muito curta!");
	}
}
