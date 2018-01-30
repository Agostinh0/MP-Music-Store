package br.ufrpe.mp_music_store.exceptions;

public class ErroAtualizarException extends Exception {

	private static final long serialVersionUID = 7773455140507660535L;

	public ErroAtualizarException() {
		super("Não foi possível atualizar.");
	}

}
