package br.ufrpe.mp_music_store.exceptions;

public class InvalidCadException extends Exception{

	private static final long serialVersionUID = 6304163788772016091L;
	
	public InvalidCadException() {
		super("Número de Cadastro muito longo.");
	}
}
