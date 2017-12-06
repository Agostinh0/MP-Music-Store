package br.ufrpe.mp_music_store.negocio;
import br.ufrpe.mp_music_store.dados.RepositorioVendas;
import br.ufrpe.mp_music_store.negocio.beans.Venda;

public class RegistroVendas {
	private RepositorioVendas repositorio;
	private static RegistroVendas instance;
	
	//Construtor
	public RegistroVendas(){
		this.repositorio = RepositorioVendas.getInstance();
	}
	
	//Singleton
	public static RegistroVendas getInstance(){
		if(instance == null){
			instance = new RegistroVendas();
		}
		
		return instance;
	}
	
	//Registrar venda
	public void registrarVenda(Venda venda){
		if(venda == null){
			//error message
		}else{
			repositorio.registrarVenda(venda);
		}
	}
	
	//Buscar venda
	public Venda buscarVenda(long codigo){
		if(codigo == 0){
			//error message
		}
		return this.repositorio.procurar(codigo);
	}
	
	//Remover venda do histórico
	public void remover(long codigo){
		if(codigo == 0){
			//error message
		}else{
			repositorio.remover(codigo);
		}
	}
	
	//Verificar existência de uma venda
	public boolean existeVenda(long codigo){
		if(codigo == 0){
			//error message
		}
		 return this.repositorio.existe(codigo);
	}
}