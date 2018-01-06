package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.negocio.beans.*;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

public interface IFachada {
	
	//Clientes
	void adicionarCliente(Cliente c) throws ObjectExistException;
	Cliente buscarCliente(long cpf) throws ObjectNotExistException;
	void removerCliente(long cpf) throws ObjectNotExistException, ErroRemoverException;
	void atualizarCliente(long pesquisa, Cliente c) throws ObjectNotExistException, ErroAtualizarException;
	boolean existeCliente(long cpf);
	
	//Funcionários
	void adicionarFuncionario(Funcionario f) throws ObjectExistException;
	Funcionario buscarFuncionario(long cpf) throws ObjectNotExistException;
	void atualizarFuncionario(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException;
	void removerFuncionario(long cpf) throws ObjectNotExistException, ErroRemoverException;
	boolean existeFuncionario(long cpf);
	
	//CD's
	void adicionarCd(Cd c) throws ObjectExistException;
	Cd buscarCd(String nome) throws ObjectNotExistException;
	void removerCd(String nome) throws ObjectNotExistException, ErroRemoverException;
	boolean existeCd(String titulo);
	void atualizarCd(String pesquisa, Cd c) throws ObjectNotExistException, ErroAtualizarException;
	
	//Vendas
	void registrarVenda(Venda venda) throws ObjectExistException;
	Venda buscarVenda(long codigo) throws ObjectNotExistException;
	void removerVenda(long codigo) throws ObjectNotExistException, ErroRemoverException;
	boolean existeVenda(long codigo);
	
}