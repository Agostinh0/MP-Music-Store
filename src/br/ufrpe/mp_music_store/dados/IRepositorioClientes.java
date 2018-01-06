package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

public interface IRepositorioClientes {
	
	void cadastrar(Cliente c);
	void cadastrar(String nome, long cpf, String endereco, long tel, int numCadastro, boolean tipoCliente);
	Cliente buscar(long cpf) throws ObjectNotExistException;
	boolean existe(long cpf);
	void atualizar(long pesquisa, Cliente cliente) throws ObjectNotExistException, ErroAtualizarException;
	void remover(long cpf) throws ObjectNotExistException, ErroRemoverException;
	void duplicarArrayClientes();
	
}
