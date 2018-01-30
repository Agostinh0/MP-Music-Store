package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.enumeracoes.TipoFuncionario;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

public interface IRepositorioFuncionarios {

	void cadastrar(Funcionario f);
	void cadastrar(String nome, long cpf, String endereco, long tel, float salario, int numContrato, TipoFuncionario tipoFunc);
	Funcionario buscar(long cpf) throws ObjectNotExistException;
	int procurarIndice(long cpf);
	boolean existe(long cpf);
	void atualizar(long pesquisa, Funcionario f) throws ObjectNotExistException, ErroAtualizarException;
	void remover(long cpf) throws ObjectNotExistException, ErroRemoverException;
	void duplicarArrayFuncionarios();
	ArrayList<Funcionario> listar();
	void salvarArquivo();

}
