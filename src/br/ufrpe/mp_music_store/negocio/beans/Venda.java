package br.ufrpe.mp_music_store.negocio.beans;

public class Venda {
	private Cliente cliente;
	private Cd cdVendido;
	private long codigoVenda;
	
	//Construtor
	public Venda(Cliente cliente, Cd cdVendido, long codigoVenda){
		this.cliente = cliente;
		this.cdVendido = cdVendido;
		this.codigoVenda = codigoVenda;
	}

	//Métodos Getters e Setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cd getCdVendido() {
		return cdVendido;
	}

	public void setCdVendido(Cd cdVendido) {
		this.cdVendido = cdVendido;
	}

	public long getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(long codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	
	//toString
	public String toString(){
		String texto = "CD vendido: " + this.getCdVendido();
		texto += "\nCliente :" + this.getCliente();
		texto += "\nCódigo da venda: " + this.getCodigoVenda();
		return texto;
	}
	
}
