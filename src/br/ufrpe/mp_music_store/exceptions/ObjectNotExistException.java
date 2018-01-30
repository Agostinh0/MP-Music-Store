package br.ufrpe.mp_music_store.exceptions;

public class ObjectNotExistException extends Exception {

	private static final long serialVersionUID = 2938053500539180445L;

	public ObjectNotExistException() {
		super("Não cadastrado.");
	}
}
