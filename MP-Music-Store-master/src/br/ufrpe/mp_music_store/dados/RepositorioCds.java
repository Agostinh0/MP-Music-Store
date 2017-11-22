package br.ufrpe.mp_music_store.dados;
import br.ufrpe.mp_music_store.negocio.beans.Cd;

public class RepositorioCds {
	
	private Cd[] cd;
	private int proxima;
	
	//Construtor
	public RepositorioCds(int tamanho){
		this.cd = new Cd[tamanho];
		this.proxima = 0;
	}
	
	
	//Catalogar CD
	public void cadastrar(Cd c){
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
	public Cd procurar(String nome){
		int i = this.procurarIndice(nome);
		Cd resultado = null;
		if(i != this.proxima){
			resultado = this.cd[i];
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
	
	//Remover do array de CD's
	public void remover(String nome){
		int i = this.procurarIndice(nome);
		if(i != this.proxima){
			this.cd[i] = this.cd[this.proxima - 1];
			this.cd[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}else{
			
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
}
