package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Venda;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public interface IRepositorioVendas {

	void registrarVenda(Venda venda);
	void registrarVenda(Cliente cliente, Cd cdVendido, long codigoVenda, LocalDate dataVenda, LocalTime horaVenda);
	Venda procurar(long codigo) throws ObjectNotExistException;
	int procurarIndice(long codigo);
	boolean existe(long codigo);
	void remover(long codigo) throws ObjectNotExistException, ErroRemoverException;
	void duplicarArrayVendas();
	ArrayList<Venda> listar();
	void salvarArquivo();

}
