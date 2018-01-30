package br.ufrpe.mp_music_store.negocio.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;

public class Venda implements Serializable{
	
	private static final long serialVersionUID = 3873093982631922518L;
	private Cliente cliente;
	private Cd cdVendido;
	private long codigoVenda;
	private LocalDate dataVenda;
	private LocalTime horaVenda;

	//Construtor
	public Venda(Cliente cliente, Cd cdVendido, long codigoVenda, LocalDate dataVenda, LocalTime horaVenda){
		this.cliente = cliente;
		this.cdVendido = cdVendido;
		this.codigoVenda = codigoVenda;
		this.cliente.compraCds(this.cdVendido);
		this.dataVenda = dataVenda;
		this.horaVenda = horaVenda;
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
	
	public LocalDate getDataVenda(){
		return dataVenda;
	}
	
	public void setDataVenda(LocalDate dataVenda){
		this.dataVenda = dataVenda;
	}
	
	public LocalTime getHoraVenda(){
		return horaVenda;
	}
	
	public void setHoraVenda(LocalTime horaVenda){
		this.horaVenda = horaVenda;
	}

	public double getDiscount() {
		double desconto = 0.0;
		if(this.cliente.getTipoCliente() == TipoCliente.PREMIUM) {
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
		texto += "\nData da venda: " + this.getDataVenda();
		texto += "\nHora da venda: " + this.getHoraVenda();
		texto += "\nCódigo da venda: " + this.getCodigoVenda();
		return texto;
	}

}