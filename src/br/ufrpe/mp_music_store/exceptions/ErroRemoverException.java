package br.ufrpe.mp_music_store.exceptions;

public class ErroRemoverException extends Exception {

	private static final long serialVersionUID = -7274670105397365210L;

	public ErroRemoverException() {
		super("N�o foi poss�vel remover.");
	}
}
