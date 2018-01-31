package br.ufrpe.mp_music_store.negocio;

import br.ufrpe.mp_music_store.negocio.beans.*;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.InvalidCadException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.InvalidPasswordException;
import br.ufrpe.mp_music_store.exceptions.InvalidTeleException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

public interface IFachada {

	//Clientes
	void adicionarCliente(Cliente c) throws ObjectExistException, InvalidCpfException, InvalidTeleException;
	Cliente buscarCliente(long cpf) throws ObjectNotExistException;
	void removerCliente(long cpf) throws ObjectNotExistException, ErroRemoverException;
	void atualizarCliente(long pesquisa, Cliente c) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException;
	boolean existeCliente(long cpf);
	ArrayList<Cliente> listarClientes();
	void salvarArquivoClientes();

	//Funcionários
	void adicionarFuncionario(Funcionario f) throws ObjectExistException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException;
	Funcionario buscarFuncionario(long cpf) throws ObjectNotExistException;
	void atualizarFuncionario(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException, InvalidCpfException, InvalidTeleException, InvalidCadException, InvalidPasswordException;
	void removerFuncionario(long cpf) throws ObjectNotExistException, ErroRemoverException;
	boolean existeFuncionario(long cpf);
	ArrayList<Funcionario> listarFuncionario();
	boolean verificaLogin(Usuario u);
	boolean verificaLoginAdm(Usuario u);
	void salvarArquivoFunc();
	
	//CD's
	void adicionarCd(Cd c) throws ObjectExistException;
	Cd buscarCd(String nome) throws ObjectNotExistException;
	void removerCd(String nome) throws ObjectNotExistException, ErroRemoverException;
	boolean existeCd(String titulo);
	void atualizarCd(String pesquisa,Cd c) throws ObjectNotExistException, ErroAtualizarException;
	ArrayList<Cd> listarCds();
	void salvarArquivoCds();
	
	//Vendas
	void registrarVenda(Venda venda) throws ObjectExistException;
	Venda buscarVenda(long codigo) throws ObjectNotExistException;
	void removerVenda(long codigo) throws ObjectNotExistException, ErroRemoverException;
	boolean existeVenda(long codigo);
	ArrayList<Venda> listarVendas();
	void salvarArquivoVendas();
}