package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Venda;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public class RepositorioVendas implements IRepositorioVendas{

	private Venda[] vendas;
	private int proxima;
	private static RepositorioVendas instance;
	private ArrayList<Venda> listaVendas = new ArrayList<>();

	//Construtor
	public RepositorioVendas(int tamanho){
		this.vendas = new Venda[tamanho];
		this.proxima = 0;
	}

	//Singleton
	public static RepositorioVendas getInstance(){
		if(instance == null){
			instance = new RepositorioVendas(100);
		}

		return instance;
	}

	//Registrar venda
	public void registrarVenda(Venda venda){
		this.listaVendas.add(venda);
		this.vendas[this.proxima] = venda;
		this.proxima += 1;

		if(this.proxima == vendas.length){
			this.duplicarArrayVendas();
		}
	}

	public void registrarVenda(Cliente cliente, Cd cdVendido, long codigoVenda){
		Venda venda = new Venda(cliente, cdVendido, codigoVenda);
		this.registrarVenda(venda);
	}

	//Buscar venda
	public Venda procurar(long codigo) throws ObjectNotExistException{
		int i = this.procurarIndice(codigo);
		Venda resultado = null;

		if(i != this.proxima){
			resultado = this.vendas[i];
		}
		else {
			throw new ObjectNotExistException();
		}

		return resultado;
	}

	//Buscar �ndice
	public int procurarIndice(long codigo){
		int i = 0;
		boolean achou = false;

		while((!achou) && (i < this.proxima)){
			if(codigo == this.vendas[i].getCodigoVenda()){
				achou = true;
			}else{
				i += 1;
			}

		}

		return i;
	}

	//Verificar exist�ncia da venda
	public boolean existe(long codigo){
		boolean existe = false;
		int indice = this.procurarIndice(codigo);
		if(indice != proxima){
			existe = true;
		}
		return existe;
	}

	//Remover venda do hist�rico
	public void remover(long codigo) throws ObjectNotExistException, ErroRemoverException{
		int i = this.procurarIndice(codigo);

		if(i != this.proxima){
			this.listaVendas.remove(i);
			this.vendas[i] = this.vendas[this.proxima - 1];
			this.vendas[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}else{
			throw new ErroRemoverException();
		}
	}

	//Dobrar tamanho do array de vendas
	public void duplicarArrayVendas(){
		if(this.vendas != null && this.vendas.length > 0){
			Venda[] arrayDuplicado = new Venda[this.vendas.length * 2];
			for(int i = 0; i < this.vendas.length; i++){
				arrayDuplicado[i] = this.vendas[i]; 
			}

			this.vendas = arrayDuplicado;
		}
	}

	public ArrayList<Venda> listar() {
		return this.listaVendas;
	}

}