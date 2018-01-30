package br.ufrpe.mp_music_store.exceptions;

public class InvalidCpfException extends Exception {

	static final long serialVersionUID = 3970438062505702262L;

	public InvalidCpfException() {
		super("CPF Inválido.");
	}
}
