package br.ufrpe.mp_music_store.exceptions;

public class ObjectExistException extends Exception {

	static final long serialVersionUID = -7910008357451293515L;

	public ObjectExistException() {
		super("Já cadastrado.");
	}
}
