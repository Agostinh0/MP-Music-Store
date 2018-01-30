package br.ufrpe.mp_music_store.dados;

import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;

public class RepositorioCds implements IRepositorioCds, Serializable{

	private static final long serialVersionUID = 8811218252363781686L;
	private Cd[] cd;
	private int proxima;
	private static RepositorioCds instance;
	private ArrayList<Cd> listaCds = new ArrayList<>();

	//Construtor
	public RepositorioCds(int tamanho){
		this.cd = new Cd[tamanho];
		this.proxima = 0;
	}

	//Singleton
	public static RepositorioCds getInstance() {
		if(instance == null) {
			instance = RepositorioCds.lerArquivo();
		}

		return instance;
	}

	//Catalogar CD
	public void cadastrar(Cd c){
		this.listaCds.add(c);
		this.cd[this.proxima] = c;
		this.proxima += 1;
		if(this.proxima == cd.length){
			this.duplicarArrayCds();
		}
	}

	public void cadastrar(String titulo, int ano, String artista, float preco){
		Cd c = new Cd(titulo, ano, artista, preco);
		this.cadastrar(c);
	}

	//Buscar CD pelo título
	public Cd procurar(String nome) throws ObjectNotExistException{
		int i = this.procurarIndice(nome);
		Cd resultado = null;
		if(i != this.proxima){
			resultado = this.cd[i];
		}
		else {
			throw new ObjectNotExistException();
		}

		return resultado;
	}

	//Buscar índice
	private int procurarIndice(String titulo){
		int i = 0;
		boolean achou = false;

		while((!achou) && (i < this.proxima)){
			if(titulo.equals(this.cd[i].getTitulo())){
				achou = true;
			}else{
				i += 1;
			}
		}
		return i;
	}

	//Verificar existência
	public boolean existe(String titulo){
		boolean existe = false;
		int indice = this.procurarIndice(titulo);
		if(indice != proxima){
			existe = true;
		}
		return existe;
	}

	//Atualizar informações do CD
	public void atualizar(String pesquisa, Cd c) throws ObjectNotExistException, ErroAtualizarException{
		int i = procurarIndice(pesquisa);

		if(i >= 0){
			this.cd[i].setTitulo(c.getTitulo());
			this.cd[i].setAnoLancamento(c.getAnoLancamento());
			this.cd[i].setArtista(c.getArtista());
			this.cd[i].setPreco(c.getPreco());
		}
		else {
			throw new ErroAtualizarException();
		}

	}
	
	public void atualizar(Cd c) throws ObjectNotExistException, ErroAtualizarException{
		int indice = this.procurarIndice(c.getTitulo());
		
		if(indice != this.proxima){
			this.atualizar(c.getTitulo(), c);
		}else{
			throw new ErroAtualizarException();
		}
	}
	
	//Remover do array de CD's
	public void remover(String nome) throws ObjectNotExistException, ErroRemoverException{
		int i = this.procurarIndice(nome);
		if(i != this.proxima){
			this.cd[i] = this.cd[this.proxima - 1];
			this.cd[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}else{
			throw new ErroRemoverException();
		}
	}

	//Dobrar tamanho do array de CD's
	public void duplicarArrayCds(){
		if(this.cd != null && this.cd.length > 0){
			Cd[] arrayDuplicado = new Cd[this.cd.length * 2];
			for(int i = 0; i<this.cd.length; i++){
				arrayDuplicado[i] = this.cd[i];
			}

			this.cd = arrayDuplicado;
		}
	}

	public ArrayList<Cd> listar() {
		return this.listaCds;
	}
	
	//Arquivos
	
	private static RepositorioCds lerArquivo(){
		RepositorioCds instanciaLocal = null;
		
		File arquivo = new File("repositorioCds.dat");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			
			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
		
			instanciaLocal = (RepositorioCds) o;
			
		}catch(Exception e){
			instanciaLocal = new RepositorioCds(100);
		
		}finally{
			if(ois != null){
				try{
					ois.close();
				}catch(IOException e){
					
				}
			}
		}
		
		return instanciaLocal;
		
	}
	
	public void salvarArquivo(){
		if(instance == null){
			return;
		}
		
		File arquivo = new File("repositorioCds.dat");
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			fos = new FileOutputStream(arquivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(oos != null){
				try{
					oos.close();
				}catch(IOException e){
					
				}
			}
		}
		
	}
}
