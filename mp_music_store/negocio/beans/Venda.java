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
		this.cliente.compraCds(this.cdVendido);
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
	
	public double getDiscount() {
		double desconto = 0.0;
		if(this.cliente.getClienteTypePremium()) {
			desconto = this.cdVendido.getPreco() * 0.20;
			if(this.cliente.getIndice() % 11 == 0 && this.cliente.getIndice() != 0) {
				desconto = this.cdVendido.getPreco() * 0.35;
			}
		}
		else if(this.cliente.getIndice() % 11 == 0 && this.cliente.getIndice() != 0) {
			desconto = this.cdVendido.getPreco() * 0.15;
		}
		
		return desconto;
	}
	
	public double precoTotal(double desconto) {
		return this.cdVendido.getPreco() - desconto;
	}
	
	//toString
	public String toString(){
		String texto = "CD vendido: " + this.getCdVendido();
		texto += "\nDescontos: R$" + this.getDiscount();
		texto += "\nPreço total: R$" + this.precoTotal(this.getDiscount());
		texto += "\n" + this.getCliente();
		texto += "\nCódigo da venda: " + this.getCodigoVenda();
		return texto;
	}
	
}
