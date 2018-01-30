package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public interface IRepositorioCds {

	void cadastrar(Cd c);
	void cadastrar(String titulo, int ano, String artista, float preco);
	Cd procurar(String titulo) throws ObjectNotExistException;
	boolean existe(String titulo);
	void atualizar(String pesquisa, Cd c) throws ObjectNotExistException, ErroAtualizarException;
	void atualizar(Cd c) throws ObjectNotExistException, ErroAtualizarException;
	void remover(String nome) throws ObjectNotExistException, ErroRemoverException;
	void duplicarArrayCds();
	ArrayList<Cd> listar();
	void salvarArquivo();
}
